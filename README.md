# 文件上传下载
更新一个readme

## 包结构
- easy-ud-core 核心部分
- easy-ud-sql 数据访问层
- easy-ud-server 服务端
- easy-ud-client 客户端
- easy-ud-server-starter 服务端的引用包
- ...在过程中产生的新的服务组件，比如sql包等，不断扩展


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
- 权限的管理待定



## 阶段计划
1. 0.1
- 普通文件上传（无hash校验，但预留接口），提供controller，使用swagger注解（后面一样），返回对象，包括id（读取时使用），文件原始名称，文件在服务器的绝对路径（绝对路径的前缀应该是配置）
- 普通多文件上传
- 文件读取，（大部分情况，各个项目可能会使用nginx,我们就可以不用管，但也不排除少数项目不用nginx）,图片读取的方式，一般以流的方式
- 文件下载
- 预留文件上传类型限制（避免服务器攻击），应该默认有分类，比如图片（.png,.jpg...），视频(.mp4，.avi...)，文档，脚本等，可以配置，但枚举详细
- 数据库访问。
