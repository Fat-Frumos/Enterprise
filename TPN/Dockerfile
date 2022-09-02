FROM debian:latest
RUN apt-get update && apt-get install -qq -y \
    shellcheck \
  && rm -rf /var/lib/apt/lists/*
EXPOSE 80
WORKDIR /usr/src/app/
COPY . /usr/src/app/
CMD ["/usr/src/app/docker-compose","up","--force-recreate"]
