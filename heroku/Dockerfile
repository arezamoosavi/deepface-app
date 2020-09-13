FROM ubuntu:latest

# Install OpenJDK 8
RUN \
    apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    rm -rf /var/lib/apt/lists/*

# Install Python
RUN \
    apt-get update && \
    apt-get install -y python3 python3-dev python3-pip python3-virtualenv && \
    rm -rf /var/lib/apt/lists/*

ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1
ENV PIP_FORMAT=legacy

RUN apt-get -y update && apt-get install -y libzbar-dev bash gcc git libc-dev

RUN apt-get install -y netcat && apt-get autoremove -y

RUN apt-get install -y build-essential
RUN pip3 install deepface==0.0.33

COPY requirements.txt /requirements.txt
RUN pip3 install --no-cache-dir -r /requirements.txt

RUN mkdir -p /main

COPY ./main /main

WORKDIR /main

COPY ./run_web.sh /run_web.sh
RUN chmod 777 /run_web.sh
RUN chmod -R 777 /main
RUN chmod -R 777 ./

ENV PORT=80
EXPOSE $PORT

ENTRYPOINT ["/run_web.sh"]