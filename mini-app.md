# 架构原理

## 双线程通信模型
- 渲染层
  - 多个webview，打包出html、css
- 逻辑层
  - JsCore，独立，只有js的运行环境
- Native
  - 做中转，jsbridge调用原生api。
> 渲染层和逻辑层只有数据的通信，防止开发者随意操作页面

运行环境 | 逻辑层  | 渲染层
:------ | :------: | -----:
Android | V8     |Chromium定制内核
iOS |JavaScriptCore|WKWebView
小程序开发者工具|NWJS|Chrome WebView

## 开发者工具
- 基于NW.js构建
> 类似于Electron.js，但有不同
  - 入口：
    - NW.js以index.html为入口
    - Electron.js以index.js为入口
  - 开发方式：
    - NW.js功能整合到chromium-runtime，像开发页面，同时可以使用node的api
    - Electron基于node，整合event-loop和chromium，像开发node应用，api区分前后端

- 通过webview展示页面
- 因为双线程设计，故存在视图层、逻辑层两个webview

## wxml真面目
- wxml -> js -> 插入html
- 需要处理动态绑定的数据，所以要转js
- 经过编译后渲染为html

## wxss
- wxss -> js -> css
- 需要转换rpx单位，故转js
