# 文件上传下载

## 包结构
- easy-ud-core 核心部分
- easy-ud-sql 数据访问层
- easy-ud-server 服务端
- easy-ud-client 客户端
- easy-ud-server-starter 服务端的引用包
- ...在过程中产生的新的服务组件，比如sql包等，不断扩展

## 需求相关
- 移动警务
- cowork
- 宁波教育

## 建设目标功能
- 上传
- 下载

## 细节内容
- 文件碎片化上传下载
- 使用hash校验
- 协议不能私有，一般使用http
- 上传“反悔”：上传到一半，取消的时候，已经上传的部分保留一个有效期内
- 上传过程中，不能被下载
- 上传的文件会放到临时区
- 源数据的管理，（文件存放可以是硬盘，可以是云盘等），数据库来记录文件名，长度等文件信息
- 权限的管理

##任务分配
任务分配|TODO| 负责人
:-------- | :----- | :----:							
普通文件上传|
	为保证服务器安全，上传文件应该放在外界无法直接访问的目录下|断点续传|陈	
		为防止文件覆盖的现象发生，校验文件md5值和SHA|删除上传的临时文件|陈	
		解决多个文件重名的问题|上传过程中拼接文件|陈
		防止一个目录下面出现太多文件|上传意外的处理（上传反悔）|陈	
		上传过程中不能下载|判断文件状态是否上传完成|陈
普通多文件上传|
普通文件上传解决后|多个文件同时上传|陈					
文件读取|
通过流的形式读取（图片）先实现|将大文件的读取改为下载，网络状态不佳时的处置|陈	
文件下载|
普通文件下载|断点续传|陈
无数据库时文件信息的管理|删除下载后产生的临时文件|陈	
 | |权限，同数据库相关|陈
数据库访问|
数据库增删改查|源数据的管理|杨	
			| |记录文件信息|杨	
			| |权限的管理|杨	
文件上传类型限制|待考查|杨	
附录|							
文件类型判断|如何读取16进制的文件头判断文件实际类型

0.2版本任务分配
| 目标                             | TODO                                                    | 时间节点 | 负责人 |
| -------------------------------- | ------------------------------------------------------- | -------- | ------ |
| 1.全局异常处理                   | 异常处理                                                | 9.30     | 陈     |
| 2.分块上传大文件                 | 大文件的md5读取速度慢，占用接口时间过长                 | 10.08     | 陈     |
| 3.文件分块的拼接                 | 能否在文件上传过程中同时拼接/若可以，上传“反悔”怎么处理 | 10.10     | 陈     |
| 4.断点续传                       |                                                         | 10.10     | 陈     |
| 5.临时文件管理                   | 拼接完成后的分片文件删除/拼接未完成的分片文件保留期限   | 10.12   | 陈     |
| 6.多数据源配置                   | 可以把文件信息存储到mongoDB等数据库                     | 3.30     | 杨     |
| 7.源数据的管理                   | 记录文件的删除                                          | 10.08    | 杨     |
| 8.文件类型按图片、视频等划分类别 | 可以指定文件上传类别                                    | 10.09     | 杨     |
| 9.文件下载上传限速控制           |                                                         | 10.11    | 杨     |
| 10.权限管理                      | 多场景需求配置                                          | 10.13   | 杨     |

## 阶段计划
1. 0.1
- 普通文件上传（无hash校验，但预留接口），提供controller，使用swagger注解（后面一样），返回对象，包括id（读取时使用），文件原始名称，文件在服务器的绝对路径（绝对路径的前缀应该是配置）
- 普通多文件上传
- 文件读取，（大部分情况，各个项目可能会使用nginx,我们就可以不用管，但也不排除少数项目不用nginx）,图片读取的方式，一般以流的方式
- 文件下载
- 预留文件上传类型限制（避免服务器攻击），应该默认有分类，比如图片（.png,.jpg...），视频(.mp4，.avi...)，文档，脚本等，可以配置，但枚举详细
- 数据库访问。
2. 0.2
- 大文件的上传，主要包括碎片化上传，hash校验（把普通文件的上传todo也改掉）
- 上传"反悔"功能
- 上传过程中，不能被下载

3. 1.0
上面细节内容的剩余部分

4. 2.0
上述版本完成以后的提升，TODO...
