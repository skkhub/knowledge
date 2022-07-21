## SameSite属性
> 主要解决csrf问题
- Strict
  - 完全禁止第三方cookie，只有当前url于目标一致才带cookie，过于严格。可能导致：“跳转过去总是未登录态”
- Lax
  - 只有Get请求带cookie，包括：链接、预加载请求、get表单
- None
  - 必须带上Secure才生效，否则默认。默认是Lax