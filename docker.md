# docker base knowledge

## several concept
- respository 仓库
- image 镜像
- container 容器
- Dockerfile
- tar

## 基本操作
- docker pull nginx
> 从仓库拉取最新版nginx，nginx可以后接版本号，如：nginx:1.22
- docker images
> 本地查看有哪些镜像

- docker run --name mynginx -d -p 81:80 -v `pwd`:/usr/share/nginx/html/ nginx
> 从镜像nginx中实例化一个容器, --name自定义名字，-d表示后台运行，不阻塞前台命令行，-p表示将本机的81端口映射到容器内的80端口，-v 表示路径映射
- docker ps -a
> 查看正在运行的容器, -a 参数会同时列出未运行的容器

- docker exec -it mynginx bash
> 给mynginx分配个bash命令行，可以操纵mynginx这个容器

- docker rm -f mynginx
> 强制删除mynginx这个容器。如果没有-f，则需要容器先停止运行，才能删除

- docker commit mynginx imagename
> 以mynginx为模板创造一个镜像，名字是imagename。可以通过docker images查看到。

- docker build -t imagename2 .
> 构建一个镜像，名为imagename2，配置文件是当前目录下的Dockerfile

```Dockerfile
FROM nginx
ADD ./ /usr/share/nginx/html/
```

- docker save imagename > 1.tar
> 将镜像imagename保存为1.tar

- docker rmi imagename
> 删除镜像imagename

- docker load < 1.tar
> 加载镜像1.tar，之后可通过docker images查看imagename又有了
