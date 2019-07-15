//package com.tuandai.ms.sharding;
//
//import io.shardingsphere.api.config.ShardingRuleConfiguration;
//import io.shardingsphere.api.config.TableRuleConfiguration;
//import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
//import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
//import org.apache.commons.dbcp.BasicDataSource;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class ShardingTest {
//
//    public static void main(String[] args) throws SQLException {
//        ShardingDataSource shardingDataSource = new ShardingDataSource();
//        DataSource dataSource = shardingDataSource.sharding();
//        new DataRepository(dataSource).demo();
//    }
//
//    public DataSource sharding() throws SQLException {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        BasicDataSource dataSource1 = new BasicDataSource();
//        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://127.0.0.1:3306/ds0");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("root");
//        dataSourceMap.put("ds0", dataSource1);
//
//        // 配置第二个数据源
//        BasicDataSource dataSource2 = new BasicDataSource();
//        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://127.0.0.1:3306/ds1");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("root");
//        dataSourceMap.put("ds1", dataSource2);
//
//        // 配置Order表规则
//        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
//        orderTableRuleConfig.setLogicTable("t_order");
//        orderTableRuleConfig.setActualDataNodes("ds${0..1}.t_order${0..1}");
//
//        // 配置分库 + 分表策略
//        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order${order_id % 2}"));
//        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_item_id", "t_order_item${order_item_id % 2}"));
//
//        // 配置分片规则
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 配置order_item表规则...
//        TableRuleConfiguration orderItemTableRuleConfig = new TableRuleConfiguration();
//        orderItemTableRuleConfig.setLogicTable("t_order_item");
//        orderItemTableRuleConfig.setActualDataNodes("ds${0..1}.t_order_item${0..1}");
//
//        shardingRuleConfig.getTableRuleConfigs().add(orderItemTableRuleConfig);
//
//        // 获取数据源对象
//        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
//    }
//
//
//}
