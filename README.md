# Java-SE-Core
Java基础部分练习,包括集合，并发，IO，JVM,算法，socket，xml等基础知识,持续练习...


	│  //来自《算法 第4版》
	|   jar包导入到本地maven库的命令(注意替换jar包地址)  
    | mvn install:install-file -Dfile="E:\git_repo\Java-SE-Core\lib\algs4.jar" 
    |  -DgroupId=edu.princeton.cs -DartifactId=algs4 -Dversion=4.0.0 -Dpackaging=jar
	├─algorithm4th
	│  └─chapter2
	│          Example.java
	│          Insertion.java
	│          Selection.java
	│          Shell.java
	│          
	├─api
	| //常用集合类
	├─collection
	│  ├─list
	│  │      ArrayListDemo.java
	│  │      LinkedListDemo.java
	│  │      
	│  ├─map
	│  │      HashMapDemo.java
	│  │      LinkedHashMapDemo.java
	│  │      TreeMapDemo.java
	│  │      WeakHashMapDemo.java
	│  │      
	│  └─set
	│          HashSetDemo.java
	│          LinkedHashSetDemo.java
	│          TreeSetDemo.java
	│
	| //多线程工具类
	├─concurrent
	│      BlockingQueueDemo.java
	│      ConcurrentHashMapDemo.java
	│      CountDownLatchDemo.java
	│      CyclicBarrierDemo.java
	│      DelayQueueDemo.java
	│      ExchangerDemo.java
	│      FutureTaskDemo.java
	│      ScheduledThreadPoolExecutorDemo.java
	│      SemaphoreDemo.java
	│      SynchronousQueueDemo.java
	│
	| //简单的io操作示例
	├─io
	│      CopyOfFileCopy.txt
	│      DataStream.txt
	│      DataStreamDemo.java
	│      DirList.java
	│      FileCopy.java
	│      InputStreamDemo.java
	│      ObjectStream.txt
	│      ObjectStreamDemo.java
	│ 
	| //理解nio
	├─nio
	│      ByteBufferDemo.java
	│      ServerConnect.java
	│
	| //网络通信，主要是socket和udp
	├─socket
	│      SocketClient.java
	│      SocketServer.java
	│      UDPClient.java
	│      UDPServer.java
	│
	|//xml的读写四种方式：JDK自带的dom和sax,常用工具JDom和Dom4J
	└─xml
	        Book.java
	        books.xml
	        bookswritenbydom.xml
	        bookswritenbysax.xml
	        Dom4JDemo.java
	        DomDemo.java
	        JDomDemo.java
	        rssnewswritenbydom4j.xml
	        rssnewswritenbyjdom.xml
	        SaxDemo.java
	        SaxParseHandler.java
	        
