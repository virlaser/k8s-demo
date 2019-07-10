# k8s demo

使用Spring Boot搭建了一个包含有状态的MySQL和无状态的Redis的服务，每次访问页面会在页面中打印访问的时间以及访问的IP。
然后向MySQL和Redis中插入访问的时间和访问的IP。

## 部署

使用kubernetes部署整套服务，服务器分为一台master和两台node，Spring Boot服务使用deployment部署，包含三个实例；MySQL使用statefulset部署；Redis使用普通pod部署。
其中为有状态的MySQL提供了数据持久卷，使得MySQL发生故障被迁移后还能保留之前存储在nfs上的数据。

* 应用打包: mvn package
* docker打包: mvn package docker:build
* 应用打标签: docker tag [imgName] [taggddName]
* 应用上传docker hub: docker push [taggedName]

而后通过在master中运行部署文件对镜像部署
* kubectl create -f [fileName]

## 运行实例

```bash
[vlaser@localhost ~]$ clear
[vlaser@localhost ~]$ kubectl get pods -o wide
NAME                                 READY   STATUS    RESTARTS   AGE     IP            NODE               NOMINATED NODE   READINESS GATES
mysql-ss-0                           1/1     Running   0          23h     10.244.2.12   node2.vlaser.com   <none>           <none>
redis-rc-9q8jv                       1/1     Running   0          2d19h   10.244.1.6    node1.vlaser.com   <none>           <none>
spring-deployment-674c8d6b75-5hdsw   1/1     Running   2          24h     10.244.2.11   node2.vlaser.com   <none>           <none>
spring-deployment-674c8d6b75-jw87t   1/1     Running   2          24h     10.244.1.16   node1.vlaser.com   <none>           <none>
spring-deployment-674c8d6b75-tpggb   1/1     Running   2          24h     10.244.1.15   node1.vlaser.com   <none>           <none>
[vlaser@localhost ~]$ kubectl get svc -o wide
NAME         TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE     SELECTOR
kubernetes   ClusterIP   10.96.0.1        <none>        443/TCP          3d15h   <none>
mysql        ClusterIP   10.101.183.137   <none>        3306/TCP         39h     app=mysql
mysql-ss-0   NodePort    10.97.175.51     <none>        3306:30075/TCP   24h     zkInst=0
redis-svc    NodePort    10.105.226.148   <none>        6379:30003/TCP   2d19h   app=redis-pod
spring-svc   NodePort    10.99.246.26     <none>        8080:30013/TCP   24h     app=spring-pod
[vlaser@localhost ~]$ kubectl get deployment -o wide
NAME                READY   UP-TO-DATE   AVAILABLE   AGE   CONTAINERS   IMAGES                 SELECTOR
spring-deployment   3/3     3            3           24h   springdemo   vlaser/springdemo:v4   app=spring-pod
[vlaser@localhost ~]$ kubectl get pv -o wide
NAME      CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS   CLAIM               STORAGECLASS   REASON   AGE   VOLUMEMODE
pv-demo   1Gi        RWO            Recycle          Bound    default/mysql-pvc                           41h   Filesystem
[vlaser@localhost ~]$ kubectl get pvc -o wide
NAME        STATUS   VOLUME    CAPACITY   ACCESS MODES   STORAGECLASS   AGE   VOLUMEMODE
mysql-pvc   Bound    pv-demo   1Gi        RWO                           41h   Filesystem
[vlaser@localhost ~]$ kubectl get statefulset -o wide
NAME       READY   AGE   CONTAINERS   IMAGES
mysql-ss   1/1     40h   mysql        mysql
```



