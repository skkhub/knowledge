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
