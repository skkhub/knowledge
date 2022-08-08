## 什么是线程池，nodejs中哪个库处理它
- 由libuv库处理。libuv是一个多平台C库，它支持基于异步I/O的操作，例如文件系统、网络、并发

## 如何通过集群提高nodejs的性能？
- nodejs应用程序在单个处理器上运行，默认情况下不会利用多核系统。
- 集群模式会启动多个nodejs进程，从而拥有多个事件循环实例。
- 当我们开始在后台的nodejs应用程序中使用集群时，会创建多个nodejs进程，但还有一个称为集群管理器的父进程，负责监控各实例的健康状况

### 多进程、多线程
- pm2带有集群模式，可以做到负载均衡，还可以开启自启
- node自带有cluster模块，但一般都直接使用pm2
- child_process模块可以生成任何可执行文件
- worker_threads更轻量

## nodejs是如何工作的
- 使用2种线程
    - eventloop 处理的主线程
    - worker pool 中的几个辅助线程
    - 一般的IO操作如fs，CPU操作如crypto，都会使用单独的线程（由libuv）
    - 在 Node.js v10.5.0 之后，可以使用worker_threads创建线程，执行复杂计算