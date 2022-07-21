## webpack 5 新特性
### 清除过期功能
### 针对长期缓存的优化
- 内容哈希

### **模块联邦**
- 允许多个webpack一起工作，让跨应用真正做到模块共享
- 之前是个普通的插件，ModuleFederationPlugin，在5中已经作为内置模块了

### 对web平台功能的新支持
- JSON模块
- ProgressPlugin插件的优化
- 自动添加唯一命名
- typescript类型
  - 5 可以从源码中生成typescript类型，并通过npm包暴露他们

### 构建优化
- tree-shaking
  - 内部模块
  - 嵌套的，能够跟踪对导出的嵌套属性的访问，因此可以改善重新导出命名空间对象时的 
  - commonjs

- 代码块拆分与模块大小

### 性能优化
- 持久缓存