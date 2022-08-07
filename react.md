## Hooks
- useState
> 变成响应式
```js
const Home = () => {
  const [a, setA] = useState(100);
  function plus() {
    // setA(a + 100);
    setA(function(prev) {
      return prev + 100;
    })
  }

  return <div>
    {a}
    <button onClick={plus}>plus</button>
  </div>
}
```
- useEffect
> 依赖变动而跟随执行
```js
useEffect(() => {
  // js
}, [data]);
```
- useLayoutEffect
> useLayoutEffect 同步执行，会在渲染之前执行
> useEffect 异步执行，会在渲染之后执行，可能导致闪屏

### useRef
- 方法，返回一个可变的ref对象，包含current属性
- 每次返回的对象是同一个，且整个生命周期内保持不变
- 对象的值发生改变后不会触发render

### useMemo
- useMemo(() => {}, [deps]);
- 可以用做函数组件的返回值，替代React.memo

### useCallback
- 返回个带缓存的func，如果函数组件内部定义了function并且用到了内部变量，可以考虑使用useCallback包裹


## react
### 特点
- 声明式
- 组件化
- 通用性
  - 虚拟DOM的好处
    - 减少渲染次数
    - 使其不局限于web开发，只要兼容虚拟DOM
### react是什么？
- React是一个网页UI框架，通过组件化的方式解决视图层开发复用的问题，本质是一个组件化框架。
- 它的核心设计思路有三点，分别是声明式、组件化、通用性
- 声明式的优势在于直观与组合
- 组件化的优势在于视图的拆分与模块复用，可以更容易做到高内聚低耦合
- 通用性在于一次学习，随处编写。比如RN React360等，主要靠虚拟DOM保证实现。这使得React的适用范围变得足够广，无论是web、native、vr，甚至shell应用都可以进行开发。这也是react的优势
- 但作为一个视图层框架，React的劣势也十分明显。它并没有提供完整的一揽子解决方案，在开发大型前端应用时，需要向社区寻找并整合解决方案。虽然一定程度上促进了社区的繁荣，但也为开发者在技术选型和学习使用上造成了一定成本。

### 为什么使用JSX
- 一句话解释jsx
  - 是一个js的语法扩展，结构类似xml
- 核心概念
  - React本身不强制使用jsx
  - 会被转换为react.createElement，所以像是语法糖
  - React不想引入太多js本身以外的开发体系，而是通过合理的关注点分离，保持组件开发的纯粹性
- 方案对比
  - 模板
    - react团队认为模板不应该是开发过程中的关注点
    - 引入模板语法模板指令等概念被认为是不好的实现方案
  - 模板字符串
    - 结构描述复杂
    - 语法提示差
  - JXON
    - 语法提示差

### babel插件如何实现jsx到js的编译
1. babel读取代码并解析生成ast
2. 将ast传入插件层进行转换（jsx -> react.createElement）

### 如何避免声明周期中的坑
- 概念：生命周期，挂载-> 更新-> 卸载

- 挂载阶段：组件从初始化到完成加载的过程
  - 社区中去除了constructor
    - 不推荐在其中处理初始化以外的逻辑
    - 它不属于React的生命周期，只是class的初始化函数
    - 移除使代码更简洁
  - getDerivedStateFromProps 使组件在props变化时更新state
    - 执行时机
      - props被传入时
      - state发生变化时
      - forceUpdate被调用时
  - shouldComponentUpdate 主要用于性能优化
  - componentDidCatch 渲染错误只能在此周期获取
  - componentWillUnmount 在卸载前及时取消计时器或监听等操作

### 请求应该放到哪个周期？
- componentDidMount是最好的选择
- constructor从定位而言不推荐，主要用于初始化state与函数绑定，不承载业务逻辑，且类属性的流行，致使它很少使用
- componentWillMount已被标记废弃

### 类组件与函数组件有什么区别
#### 共同点
- 实际用途一样，都可作为基础组件展示UI

#### 不同点
- 心智模型：OOP FP
- 使用场景：生命周期
- 独有功能：生命周期
- 设计模式：继承
- 未来趋势：Hooks
- 性能优化：
  - 类组件 shouldComponentUpdate 阻断渲染
  - 函数组件 React.memo 缓存渲染结果

### 如何设计react组件
#### 设计分类
- 展示组件
  - 代理组件
  - 样式组件
  - 布局组件
- 灵巧组件
  - 容器组件
  - 高阶组件
    - 逻辑复用
    - 链式调用
    - 渲染劫持
    - 缺陷
      - 丢失静态函数
      - refs属性不能透传

#### 工程实践
- 目录结构划分
- 引入工程管理

### setState是同步场景还是异步场景
#### 合成事件
- React给document、渲染节点挂上事件监听
- DOM事件触发后冒泡到document
- React找到对应的组件，造出一个合成事件
- 按组件树模拟一遍事件冒泡

#### 分场景
- 控制点：isBatchingUpdates
- 同步更新场景：原生事件中，如：addEventListener\setTimeout\setInterval

- 异步场景
  - 非真异步
  - 场景：
    - react生命周期
    - 合成事件
      - 执行顺序
        - 17以前：挂载到document
        - 17：挂载到渲染节点
      - 兼容性更强
      - 提升性能
  - 原因：保持内部一致性、启用并发更新

  
### 虚拟DOM的工作原理是什么
> 通过js对象模拟DOM节点
#### 优点
  性能优越
  规避xss
  可跨平台
  - 大量的直接操作DOM容易引起网页性能下降，这时React基于虚拟DOM的diff处理与批处理操作，可降低DOM的操作范围与频次，提升页面性能
  - 首次渲染和微量操作时渲染速度比直接渲染更慢
  - 虚拟DOM内部确保字符转义，但留有dangerouselySetInnerHTML API绕过转义

  - 虚拟DOM跨平台的成本更低

#### 缺点
- 内存占用高：对象表示包含了完整的dom信息
- 无法进行极致优化

#### 其他应用
- 埋点统计
- 数据记录

### React的渲染流程
- Fiber Reconciler 是16及以后版本的渲染方案，核心设计是增量渲染，将渲染工作分割为多区块，将其分散到多个帧中执行

### 分析和调优性能瓶颈
- 网页APM工具（Application Performance Moniter）
- FCP 首次绘制内容的耗时
- TTI 页面可交互时间
- 静态资源及API请求成功率：通过performance.getEntries()
- TP(Top Percentile)
  - TP50：高于50%的情况
  - TP90：高于90%的情况

> 如果一个移动端页面加载时长超过3秒，用户就会离开

> 我负责的业务是crm管理后台，用户付费进行操作使用，有一套标准的业务流程。在我做完性能优化以后，付费率提升了17%，效果还可以。前期管理后台的基础性能数据是没有的，我接手后接入了一套APM工具，才有了基础的性能数据。然后我对指标观察了一周多，思考了业务形态，发现其实用户对后台系统加载速度要求并不高，但对性能的稳定性要求比较高。我也发现静态资源的加载成功率并不高，TP99的成功率大约在91%，这是因为静态资源直接从服务器拉取，服务器宽带形成了瓶颈，导致加载失败。我对webpack的构建工作流做了改造，支持发布到cdn。改造后，TP99提升到了99.9%。

### 状态管理框架rematch
- 基于redux
- 减少了书写复杂度
- 全局分发器（dispatch）

### react hooks的使用限制
### what
  - 不能在循环、条件、嵌套函数中使用hook
  - 只能在函数组件中使用
### why
  - 设计初衷：改进组件开发模式
  - 问题领域
    - 组件间难以复用状态逻辑
    - 复杂的组件难以理解
    - 人和机器都容易混淆类
  - 方案原理：not magic,just arrays
### how: eslint-plugin-react-hooks

### React常用的工具库
#### 初始化
- create-react-app
  - 创建工程
  - react-app-rewired配置
- dva 一站式解决方案
- umi 一站式解决方案
- create-react-library 创建库
- StoryBook 维护大规模组件

#### 开发
- 路由：react-router
- 样式
  - css模块化
  - css in js
    - emotion 提供props接口消灭内联样式
    - styled-components 通过模板字符串提供基础的样式组件
- 基础组件：antd
- 功能组件
  - 拖拽：react-dnd\react-draggable
  - 预览pdf：react-pdf-viewer
  - 视频播放：Video-React
  - 长列表：react-window
- 状态管理：Redux、Mobx

#### 构建
- 构建
  - webpack 常规大型项目交付
  - rollup 库交付
  - esbuild 高性能构建
#### 检查
- 代码规范检查：eslint
- 代码测试
  - jest 测试框架
  - enzyme 测试工具库
  - react-testing-library 针对react dom的测试工具库
  - react-hooks-testing-library 针对hooks的测试工具库

#### 发布
- s3-plugin-webpack 发布静态文件到s3[亚马逊对象存储服务]（https://aws.amazon.com/cn/s3/）新用户免费12个月
