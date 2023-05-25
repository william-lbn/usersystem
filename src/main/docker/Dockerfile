#指定基础镜像源,以其为基础进行制作
FROM iits/jdk19:jdk-19-alpine
# 维护者信息
MAINTAINER libin "909445583@qq.com"

#指定环境变量 工作目录
ENV WORKDIR "/home/jdk-user/"

#指定工作目录
WORKDIR ${WORKDIR}

#将文件添加到容器中
ADD ./file/*  ${WORKDIR}

# 运行jar包
CMD ["java","-jar","usersystem-0.0.1.jar","-Dspring.config.location=application.yml","-Dfile.encoding=utf-8"]