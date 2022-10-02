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
> 挂载还可以用`-v anyname:/app`的方式，即左边不写本机具体目录，而是一个名字，这样就是由容器创建和管理，但其位于宿主机，其他容器可以通过名字复用
> tmpfs mount 适合存储临时文件，存宿主机内存中，不可多容器共享

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

## 多容器通信
### 创建网络
- docker network create test-net
- docker run -d --name redis --network test-net --network-alias my-redis redis:last
> 将redis实例化一个容器并使用网络test-net，别名为my-redis
```js
redis.createClient({url: 'redis://my-redis:6379'});
```
- docker run -d -p 80:80 --name test -v D:/test:/app --network test-net test:v1
> 运行其他使用redis的容器，并设置网络为test-net

## Docker-Compose

### 常用命令
- docker-compose ps

- docker-compose stop

- docker-compose exec name bash
> 这里的name是配置文件yml里的services下的名字

## 发布
- docker login -u username
- docker tag imagename:verson username/xxx:version
- docker push username/xxx:version
- docker run -dp 80:80 -d username/xxx:version
> 可以直接部署

## 备份和迁移
- 大体思路：用一个容器做中转，该容器将需要备份的volume和宿主机的某个接收目录同时挂载上，然后进入该容器内部做转移
### backup
- docker run --rm --volumes-from anycontainer -v d:/backup:/backup middlecontainer tar cvf /backup/backup.tar /data/
> --rm 运行完成后自动删除该容器
> --volumes-from 按照给定的容器中的volumes配置挂载

### restore
- docker run --rm --volumes-from anycontainer -v d:/backup:/backup middlecontainer bash -c "cd /data/ && tar xvf /backup/backup.tar --strip 1"
