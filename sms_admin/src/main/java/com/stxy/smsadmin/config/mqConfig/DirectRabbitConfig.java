package com.stxy.smsadmin.config.mqConfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    /**
     * 创建人：张博
     * 时间：2018/3/5 上午10:45
     *
     * @apiNote 定义直连交换器
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    /**

     * @apiNote 定义自动删除匿名队列
     */
    @Bean
    public Queue autoDeleteQueue0() {
        return new AnonymousQueue();
    }

    /**

     * @apiNote 定义自动删除匿名队列
     */
    @Bean
    public Queue autoDeleteQueue1() {
        return new AnonymousQueue();
    }

    /**

     * @param directExchange   直连交换器
     * @param autoDeleteQueue0 自动删除队列
     * @return Binding
     * @apiNote 绑定使用路由键为 orange 的 autoDeleteQueue0 队列到直连交换器上
     */
    @Bean
    public Binding binding0a(DirectExchange directExchange, Queue autoDeleteQueue0) {
        return BindingBuilder.bind(autoDeleteQueue0).to(directExchange).with("orange");
    }

    /**

     * @param directExchange   直连交换器
     * @param autoDeleteQueue0 自动删除队列
     * @return Binding
     * @apiNote 绑定使用路由键为 black 的 autoDeleteQueue0 队列到直连交换器上
     */
    @Bean
    public Binding binding0b(DirectExchange directExchange, Queue autoDeleteQueue0) {
        return BindingBuilder.bind(autoDeleteQueue0).to(directExchange).with("black");
    }

    /**

     * @param directExchange   直连交换器
     * @param autoDeleteQueue1 自动删除队列
     * @return Binding
     * @apiNote 绑定使用路由键为 black 的 autoDeleteQueue1 队列到直连交换器上
     */
    @Bean
    public Binding binding1a(DirectExchange directExchange, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("black");
    }

    /**

     * @param directExchange   直连交换器
     * @param autoDeleteQueue1 自动删除队列
     * @return Binding
     * @apiNote 绑定使用路由键为 green 的 autoDeleteQueue1 队列到直连交换器上
     */
    @Bean
    public Binding binding1b(DirectExchange directExchange, Queue autoDeleteQueue1) {
        return BindingBuilder.bind(autoDeleteQueue1).to(directExchange).with("green");
    }
}
