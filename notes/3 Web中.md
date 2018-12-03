### REST

*Representational State Transfer* 表述性状态传递

*is one way of providing interoperability between computer systems on the Internet.*

#### 历史

来自于Roy Thomas Fielding 2000年的博士论文 - 《Architectural Styles and the Design of Network-based Software Architectures》

#### 特点

- REST的数据传递格式不限于json, XML/HTML等都可以作为其格式

#### REST成熟度模型(RMM)

*RMM: Richardson Maturity Model*  由Leonard Richardson发明的RESTful成熟度模型

参考:  1. [通往真正REST的步骤](https://blog.csdn.net/dm_vincent/article/details/51341037)  2. [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

![Steps toward the glory of REST](images/3 Web中-1.png)

- *Steps toward the glory of REST*

**以预约医生为例讲解该模型的四个层次**

- slot: 槽, 本例中类似于可预约时段的意思
- open slot: 开口槽, 本例中类似预约的意思
- appointment: 预约
- patient/doctor: 病人/医生

##### Level 0: The Swamp of POX(简单陈旧XML的困境)

*POX: Plain Old XML*  本例以json作为数据传递格式进行讲解

- 获取预约时段

  ```json
  POST /appointmentService
  
  请求参数
  {
      "openSlotRequest": {
          "date": "2010-01-04",
          "doctor": "mjones"
      }
  }
  ```

  ```json
  200 OK
  
  响应
  {
      "doctor":"mjones",
      "openSlotList": [
          {
              "id": "1234",
              "start": "14:00",
              "end": "14:50"
          },
          {
              "id": "5678",
              "start": "16:00",
              "end": "16:50"
          }
      ]
  }
  ```

- 预约

  ```json
  POST /appointmentService
  
  请求参数
  {
      "appointmentRequest": {
          "doctor": "mjones",
          "patinet": "jsmith",
          "slot": {
              "id": "1234",
              "start": "14:00",
              "end": "14:50"
          }
      }
  }
  ```

  ```json
  200 OK
  
  响应
  {
      "doctor": "mjones",
      "patinet": "jsmith",
      "slot": {
  		"id": "1234",
          "start": "14:00",
          "end": "14:50"
      }
  }
  ```

- **特点**

  - 仅使用HTTP作为传输, 而不使用其任何机制
  - 一个uri, 根据参数不同进行不同的操作
  - 没有根据HTTP动词进行特定的操作. 如本例全部使用POST进行查询和新增操作

##### Level 1: Resources(资源)

- 获取预约时段

  ```json
  POST /doctors/mjones/openSlot
  
  请求参数
  {
      "date": "2010-01-04"
  }
  ```

  ```json
  200 OK
  
  响应
  {
      "doctor":"mjones",
      "openSlotList": [
          {
              "id": "1234",
              "start": "14:00",
              "end": "14:50"
          },
          {
              "id": "5678",
              "start": "16:00",
              "end": "16:50"
          }
      ]
  }
  ```

- 预约

  ```json
  POST /doctors/mjones/slots/1234
  
  请求参数
  {
      "patient": "jsmith"
  }
  ```

  ```json
  201 Created
  
  响应
  {
      "doctor": "mjones",
      "patinet": "jsmith",
      "slot": {
  		"id": "1234",
          "start": "14:00",
          "end": "14:50"
      }
  }
  ```

- **特点**

  - 根据资源进行不同操作, 即根据不同的URI进行资源操作
  - 构建分布式系统的一个前提

##### Level 2: HTTP Verbs(HTTP动词)

HTTP Verbs即HTTP请求方式, 包括: GET/POST/PUT/DELETE

- 获取预约时段

  ```json
  GET /doctors/mjones/slots?date=2010-01-04&status=open
  ```

  ```json
  200 OK
  
  响应
  {
      "doctor":"mjones",
      "openSlotList": [
          {
              "id": "1234",
              "start": "14:00",
              "end": "14:50"
          },
          {
              "id": "5678",
              "start": "16:00",
              "end": "16:50"
          }
      ]
  }
  ```

- 预约

  ```json
  POST /doctors/mjones/slots/1234
  
  请求参数
  {
      "patient": "jsmith"
  }
  ```

  ```json
  201 Created
  
  响应
  {
      "doctor": "mjones",
      "patinet": "jsmith",
      "slot": {
  		"id": "1234",
          "start": "14:00",
          "end": "14:50"
      }
  }
  ```

- **特点**

  - 合理使用HTTP动词, 以HTTP协议定义的方式使用
  - GET: 查, POST: 增, PUT: 改, DELETE: 删

##### Level 3: Hypermedia Controls(超媒体控制)

- 获取预约时段

  ```json
  GET /doctors/mjones/slots?date=2010-01-04&status=open
  ```

  ```json
  200 OK
  
  响应
  {
      "doctor":"mjones",
      "openSlotList": [
          {
              "id": "1234",
              "start": "14:00",
              "end": "14:50"
          },
          {
              "id": "5678",
              "start": "16:00",
              "end": "16:50"
          }
      ],
      "links": [
          {
              "rel": "/linkrels/slot/appointment",
              "uri": "/doctors/mjones/slots/1234"
          },
          {
              "rel": "/linkrels/slot/appointment",
  			"uri": "/doctors/mjones/slots/5678"
          }
      ]
  }
  ```

- 预约

  ```json
  POST /doctors/mjones/slots/1234
  
  请求参数
  {
      "patient": "jsmith"
  }
  ```

  ```json
  201 Created
  
  响应
  {
      "doctor": "mjones",
      "patinet": "jsmith",
      "slot": {
  		"id": "1234",
          "start": "14:00",
          "end": "14:50"
      },
      "links": [
          {
              "rel": "/linkrels/appointment/cancel",
              "uri": "/doctors/mjones/slots/1234",
              "method": "DELETE"
          },
          {
              "rel": "self",
              "uri": "/doctors/mjones/slots/1234",
              "method": "POST"
          }
      ]
  }
  ```

- **特点**

  - *HATEOAS: Hypermedia as The Engine of Application State*  使用超媒体作为应用状态引擎
  - 每个响应中都包含了下一步可以进行的请求信息(links)
  - links中, rel指定了关系类型, uri指定了目标uri
  - 在rel不变的前提下, 更改uri时前端不受影响(感觉用处不是很大)

##### 总结

- Level1 解释了如何通过分治法(Divide and Conquer)来处理复杂问题, 将一个大型服务端点(Service Endpoint 即接口)分解成多个资源
- Level2 引入HTTP标准动词, 用来以相同的方式(uri)来应对不同的场景, 移除不必要的变化
- Level3 引入可发现行(Discoverability 即links), 让协议拥有自描述能力


#### 类似形式

- WSDL(*Web Services Description Language*)
- SOAP(*Simple Object Access Protocol*)

### 架构属性

- 性能(Performance)
- 可伸缩性(Scalability)
- 统一接口简化性(Simplicity of a uniform Interface)
- 组件可修改性(Modifiability of components)
- 组件通讯可见性(Visibility of communication between components)
- 组件可移植性(Portability of component)
- 可靠性(Reliability)

### 架构约束

- C/S架构(Client-Server)
- 无状态(Stateless)
- 可缓存(Cacheable)
- 分层系统(Layered System)
- 按需代码(Code on demand)
- 统一接口(Uniform interface)