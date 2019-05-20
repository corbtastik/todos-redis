## csvkit-async

Example using Spring Boot 2.0's webflux module along with Reactive Redis.

CsvKitAPI defines 3 http endpoint(s)

The POST endpoint saves text under a tag

``POST localhost:8080/csv/{tag}``

The GET endpoint pulls text saved under a tag

``GET localhost:8080/csv/{tag}``

The DELETE endpoint removes text saved under a tag

``DELETE localhost:8080/csv/{tag}``

Examples using [httpie](https://httpie.org/)

**POST to save text**

```bash
cmartin@corbett:~/dev/github/csvkit-async|master 
> echo 'one,fish,two,fish' | http localhost:808/csv/drseuss 
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

howdy,yo,thank,you,for,tag,drseuss,yummy,yum,yum

```

**POST text again to same tag**

```bash
cmartin@corbett:~/dev/github/csvkit-async|master 
>  echo 'red,fish,blue,fish' | http localhost:8080/csv/drseuss 
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
transfer-encoding: chunked

howdy,yo,thank,you,for,tag,drseuss,yummy,yum,yum
```

**GET text under a tag**

```bash
cmartin@corbett:~/dev/github/csvkit-async|master 
>  http localhost:8080/csv/drseuss                     
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
transfer-encoding: chunked

one,fish,two,fish
red,fish,blue,fish
```

**DELETE a tag**

```bash
cmartin@corbett:~/dev/github/csvkit-async|master 
>  http DELETE localhost:8080/csv/drseuss
HTTP/1.1 200 OK
content-length: 0


cmartin@corbett:~/dev/github/csvkit-async|master 
> http localhost:8080/csv/drseuss       
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
transfer-encoding: chunked
```

## Refs

* [Spring Boot 2.0 - Webflux](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-webflux)
* [Spring Data - reactive redis](https://docs.spring.io/spring-data/redis/docs/2.0.5.RELEASE/reference/html/#redis:reactive)
