import java.util.Calendar;

/**
 * snowflake算法
 * twitter开源的分布式id生成算法，就是将一个64位的long型的id，1个bit是不用的，用其中的
 * 41bit作为毫秒数，用10个bit作为工作机器id，12个bit作为序列号。
 *
 * 1 bit：不用，为啥呢？因为二进制里第一个bit位如果是1，那么都是负数，但是我们生成的id都是正数，
 * 所以第一个bit统一都是0；
 *
 * 41 bit：表示的是时间戳，单位是毫秒，41bit可以表示的数字多达2^41-1，也就是可以标识2^41-1个毫
 * 秒值，换算成年就是表示69年的时间。
 *
 * 10 bit：记录工作机器id，代表的是这个服务最多可以部署在2^10台机器上，也就是1024台机器。但是10bit
 * 里5个bit代表机房id，5个bit代表机器id。意思就是最多代表2^5个机房（32个机房），每个机房里可以代表
 * 2^5个机器（32台机器）。
 *
 * 12 bit：这个是用来记录同一个毫秒内产生的不同id，12 bit可以代表的最大正整数是2^12-1=4096，也就是
 * 说可以用这个12bit代表的数字来区分同一个毫秒内的4096个不同的id
 *
 * 64位的long型的id，64位的long->二进制
 *
 * 0|0001100 10100010 10111110 10001001 01011100 00|10001|11001|0000 00000000
 *
 * 2018-01-01 10:00:00 -> 做了一些计算，再换算成一个二进制，41bit ->
 *
 * 0001100 10100010 10111110 10001001 01011100 00
 *
 * 机房id，17 -> 换算成一个二进制 -> 10001
 * 机器id，25 -> 换算成一个二进制 -> 11001
 *
 *
 */
public class SnowFlake {
    private long workerId;
    private long datacenterId;
    private long sequence;

    private long workerIdBits = 5L;
    private long datacenterIdBits = 5L;
    //这个是二进制运算，就是5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
    private long maxWorkedId = -1L ^ (-1L << workerIdBits);

    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private long sequenceBits = 12L;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerIdShift = sequenceBits;
    private long datacenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    private long lastTimestamp = -1L;

    private long twepoch = 1288834974657L;

    public SnowFlake(long workerId,long datacenterId,long sequence){
        // 这儿不就检查了一下，要求就是你传递进来的机房id和机器id不能超过32，不能小于0
        if(workerId > maxWorkedId || workerId < 0){
            throw new IllegalArgumentException(String.format(
                    "worker Id can't be greater than %d or less than 0",maxWorkedId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format(
                    "datacenter Id can't be greater than %d or less than 0",maxDatacenterId));
        }
        System.out.printf(
                "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);

        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }

    public long getWorkerId(){
        return workerId;
    }

    public long getDatacenterId(){
        return datacenterId;
    }

    public long getTimestamp(){
        return System.currentTimeMillis();
    }
    private long timeGen(){
        return System.currentTimeMillis();
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    public synchronized long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();

        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                    lastTimestamp - timestamp));
        }
        // 0
        // 在同一个毫秒内，又发送了一个请求生成一个id，0 -> 1

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else{
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是将时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后10 bit；最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (datacenterId << datacenterIdShift) |
                (workerId << workerIdShift) |
                sequence;
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(1288834974657L);
        System.out.println(c.getTime());
        SnowFlake worker = new SnowFlake(1,1,1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
