## 图片优化

### 图片格式
- jpeg
> 有损压缩 栅格图形
> 不适合：线条图形和文字、图标图形
> 适合：颜色丰富的照片、结构不规则的图形

- png(Portable Network Graphics)便携式网络图形
> 无损压缩的位图图形格式，支持索引、灰度、RGB三种颜色方案及alpha通道
> 栅格图形 最高支持24位彩色图像（PNG-24）和 8位灰度图像
> 不适合：彩色图像体积太大
> 适合：纯色、透明、线条绘图，图标；边缘清晰、有大块相同颜色区域；颜色数少但半透明

- gif(Graphics Interchange Format)图像互换格式
> 栅格图形，支持256色；透明和不透明
> 不适合：彩色，每个像素只有8比特
> 适合：动画，图标

- webp 无损压缩+有损压缩
> 优秀算法 图像质量 体积小 多帧（动画效果） 透明度 8位压缩算法 无损的webp比png小26% 有损的webp比jpeg小25-34% 比gif有更好的动画
> 彩色图片（最多处理256色）
> 图形 半透明图像

### 优化方案
- js检测窗口大小
- css媒体查询动态设置图片宽度
- img标签属性 srcset="a.jpg, b.jpg 2x, c.jpg 3x"
- img标签属性 sizes="(max-width: 320px) 280px, (max-width: 480px) 440px, 800px"

### 逐步加载图像
- 统一占位符
- 使用LQIP（Low Quality Image Placeholders）低质量图像占位符
> npm i lqip
- 使用SQIP(SVG Quality Image Placeholders)基于SVG的图像占位符
> npm i sqip

### 图片替换
- Web Font 代替图片
- Data URI 代替图片（base64）
- Image spriting（雪碧图）

### 图片服务器自动优化解密
> 通过不同参数，服务器自动化生成不同格式、大小、质量的图片

## HTML优化
### 精简HTML代码
- 减少标签嵌套
- 减少DOM数量
- 减少无语义代码
- 删除http(s)?头，如果可以
- 删除多余空格、换行、缩进、不必要的注释（工具即可做到）
- 省略冗余标签和属性
- 使用相对路径的URL

### 文件放在合适的位置
- css尽量放页面头部
- js尽量放底部（defer、async）除外
> defer 不阻塞页面渲染，DOM解析完毕且在DOMContentLoaded之前执行；多个script脚本时会按书写顺序执行
> async 不阻塞页面渲染，加载完毕立即执行，多个script脚本时不保证顺序
> 动态加载脚本时，默认的行为模式是async，显式设置asnyc = false可以改为defer模式

### 增强用户体验
- 设置favicon
- 增加首屏必要的css和js（骨架背景）
