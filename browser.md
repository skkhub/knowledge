# 浏览器相关

## 结构
- 用户界面
- 浏览器引擎
- 渲染引擎（浏览器内核）
  - IE Trident
  - Firefox Gecko
  - Safari Webkit
  - Chrome/Opera/Edge Blink(based on Webkit)

## 进程
- 是应用程序运行的最小单元
- 线程是运算的最小单元
- 进程间通信管道IPC
- 同一进程下线程共享数据

## 浏览器进程
1. 浏览器进程
  - 负责用户界面（包括地址栏、书签、前进、后退，标签页除外）和协调其他进程
2. 渲染进程
  - tab标签内的内容渲染
  - 默认情况下每个tab都会创建一个进程
  - chrome共有4种进程模型
    1. process-per-site-instance 每个站点每个页面都是独立进程，默认，最安全，最占内存
    2. process-per-site 每个站点一个进程
    3. process-per-tab  每个tab下是一个进程
    4. single-process 浏览器引擎和渲染引擎共用一个进程
3. 缓存进程
4. 网络进程
  - 发起、接收网络请求
5. GPU进程
  - 整个浏览器界面渲染
6. 插件进程
  - 非插件市场安装的扩展

## 输入url后浏览器工作流程
  1. 浏览器进程的UI线程捕捉输入内容：
    - 关键词：搜索，启动默认搜索引擎查询
    - 网址：UI线程启动网络线程，请求DNS进行域名解析，接着连接服务器获取数据
  2. 网络线程获取数据（输入url）后
    1. 通过SafeBrowsing检查站点是否恶意
      - 是，展示警告页面，阻止访问
      - 否，发请求，获得响应
    2. 通知UI线程ready
  3. UI线程创建渲染器进程，同时通过IPC将数据传递过去
  4. 渲染流程
    1. DOM构建：Tokeniser -> Construction -> DOM
    2. Style
    3. Layout 不会与DOM tree一一对应，不显示的dom不会出现在Layout tree上
    4. Paint 主线程遍历Layout tree创建绘制记录表（Paint Record，记录绘制顺序）
    5. Rastering 栅格化，将上述信息转化为像素点
      - chrome使用更复杂的方法，合成（Composting）：将页面各个部分划分多个图层，分别栅格化，在合成器线程中单独合成页面的技术
      - 把可视区内容组合成一帧展示给用户
    6. 主线程遍历Layout Tree，生成Layer Tree且确定绘制顺序，将以上信息传递给合成器线程
    7. 合成器线程将每个图层分割成多个图块，交给栅格化线程
    8. 栅格化线程栅格化每个图块，存储在GPU内存中
    9. 合成器线程收集图块信息（记录有图块在内存中的位置和图块在页面中的位置），生成合成器帧， 通过IPC传送给浏览器进程
    10. 浏览器进程将合成器帧传送到GPU进程
    11. GPU渲染展示到屏幕
    
## 渲染进程总结
  - 主线程
    - DOM tree
    - style
    - layout
    - paint
    - layer
  - 合成器线程
    - 从主线程获得layer tree 和绘制顺序信息
    - 分图层，分更小的图块，传给栅格线程
    - 从栅格线程获取栅格化后的图块信息
    - 创建合成器帧
    - 通过IPC传给浏览器进程
  - 栅格线程
    - 负责栅格化及生成drawquads
## 动画卡顿优化方法
- 使用RequestAnimationFrame，在每个16ms快结束时归还主线程，执行ui操作，通过断断续修的执行js，使动画看起来流程不卡顿
- transform 只涉及合成器线程和栅格线程，无需和js抢夺主线程

## webview内核分类

### ios
- uiwebview
> 从ios2开始存在
  - 内存泄漏
  - 极高内存峰值
  - touch delay 300ms
  - js运行性能和通信限制
  - 自动注入cookie
  - 2018年ios12以后不再维护
- wkwebview
  - 内存开销小很多
  - 60fps的滚动刷新率
  - 自带支持右滑返回手势
  - 支持更多html属性
  - 可以和js直接互调函数，不像uiwebview需要第三方库webviewjsbridge来协助处理js的交互
  - 不支持页面缓存
  - 不会自动注入cookie
  - 无法发送post参数
### Android
- Webkit for webview
  - 小于 android4.4
  - 内存占用小
- chromium for webview
  - 大于 android4.4
  - 支持大多数h5
  - 内存占用大
  - 支持webauido
  - 支持webgl
  - 支持webrtc
### android第三方
- x5内核
  - 速度快
  - 省流量
  - 更安全
  - 更稳定
  - 兼容好
  - 体验优：夜间模式、适屏排版、字体设置
  - 功能全：H5\es6+支持好
  - 视频支持好，格式多，集成了强大的视频播放器
  - 视频和文件格式支持的比系统内核多
  - 防劫持：内核做了特殊处理

### 浏览器优化
- 设置全局webview
  - 牺牲初始内存
  - 提升页面加载速度