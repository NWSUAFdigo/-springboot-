### 一 静态Web

HTTP请求内容由Web服务器文件系统提供

#### 特点

- 计算类型: I/O
- 交互方式: 单一
- 资源内容: 基本相同
- 资源路径: 物料路径
- 请求方法: GET(主要)

#### 使用场景

- 信息展示
- 文件下载
- 多媒体访问(图片/视频)
- 其他(CSS/JS)

#### Web Server

- Apache HTTP Server
- Nginx
- Microsoft IIS
- Google Web Server

##### 思考: Java Web Server 与 Web Server的区别

1. 内存占用
   - 类型
   - 分配
2. 垃圾回收
   - 被动回收
   - 停顿
3. 并发处理
   - 线程池
   - 线程开销

#### Web Server优化技术

- 资源优化
  - Last-Modified 与 If-Modified-Since
- 资源缓存
  - ETag 与 If-None-Match

### 二 动态Web

### 三 模板引擎