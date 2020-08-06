Mongo image:
Na raiz execute docker run -d  -p 27017:17017 --name mymongodb mongo:latest

- Imagem para linha de onibus:
dentro da pasta raiz do container execute sudo docker  build -t linhaonibus-ms:1.0 .

- Imagem para ponto taxi:
dentro da pasta raiz do container execute sudo docker  build -t taxi-ms:1.0 .

- Linkando mongo com o container linhaonibus-ms

sudo docker run -p 8000:8000 --name linhaonibus --link mymongodb:mongo -d linhaonibus:1.0 .

- Comando para todar docker-compose
sudo docker-compse up

- Considerações finais:
Utilizei o mongodb e segui alguns de seus padrões que são diferentes de bancos
relacionais do qual estou habituado, como por exemplo oracle, postgre e etc.

