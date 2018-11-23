---
typora-root-url: ./images
---

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
  - Last-Modified(响应头) 与 If-Modified-Since(请求头)
- 资源缓存
  - ETag(响应头) 与 If-None-Match(请求头)

### 二 动态Web

HTTP请求内容由Web服务器计算得来

#### 特点

- 计算类型: 混合(I/O, CPU, 内存等)
- 交互方式: 丰富(用户输入等)
- 资源内容: 多样性
- 资源路径: 逻辑路径
- 请求方法: GET/HEAD/PUT/POST等

##### 总结

| 特点\类型 | 静态Web   | 动态Web             |
| --------- | --------- | ------------------- |
| 计算类型  | I/O       | I/O, CPU, 内存等    |
| 交互方式  | 单一      | 丰富                |
| 资源内容  | 基本相同  | 多样                |
| 资源路径  | 物料路径  | 逻辑路径            |
| 请求方法  | GET(主要) | GET/HEAD/PUT/POST等 |

#### 使用场景

- 页面渲染
- 表单交互
- AJAX
- XML
- JSON/JSONP
- Web Service(SOAP, WSDL)
- WebSocket

#### Java Web Server

- servlet容器
  - Tomcat
  - Jetty
- 非servlet容器
  - Undertow

#### 技术/架构演变

- CGI [*Common Gateway Interface*]

- Servlet

- JSP [*Java Server Page*]

- JSP + Servlet + JavaBeans

  ![2 Web上-1](/2 Web上-1.png)

- MVC (Model + View + Controller)

  ![2 Web上](/2 Web上-2.png)

- Struts

  ![](/2 Web上-3.png)

- Spring MVC

  ![](/2 Web上-4.png)

##### MVC与Struts/SpringMVC差异

1. MVC面向所有应用场景, 如PC/无线应用, Struts/SpringMVC面向web服务
2. Struts/SpringMVC中的controller细化为Front Controller和Application Controller, 前者负责路由后者, 后者负责控制页面跳转

### 三 模板引擎

#### JSP

#### Velocity

#### Thymeleaf