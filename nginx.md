# nginx学习
- 反向代理
- 负载均衡
- 动静分离

## 访问控制阶段（生命周期）
1. rewrite
2. access
> deny all;
3. content
> return 200 "content";

## 反向代理
> 反向代理，代理的是服务器。访问来到服务端，先经过nginx，由nginx做负载均衡，分配具体的处理服务器，然后再将处理服务器的返回作为响应。
> 正向代理，代理的是用户的访问。用户先访问nginx，然后nginx响应。
- proxy_pass
```nginx
location /demo/123 {
    # 最后是否加/会导致不一样的结果
    proxy_pass https://www.google.com; # 会是https://www.google.com/demo/123;
    proxy_pass https://www.google.com/; # 会是https://www.google.com/123;
    # 添加代理服务器的头，进一步确保其他资源的加载
    proxy_set_header $proxy_host;
}

```
