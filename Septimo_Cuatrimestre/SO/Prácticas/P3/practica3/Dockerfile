FROM debian:12

# Install dependencies
RUN echo "deb http://deb.debian.org/debian/ bookworm main" > /etc/apt/sources.list \
    && echo "deb-src http://deb.debian.org/debian/ bookworm main" >> /etc/apt/sources.list \
    && apt-get update \
    && apt-get install -yy \
    wget \
    xz-utils \
    tar \
    && apt-get install -yy \
    build-essential libssl-dev zlib1g-dev \
    libbz2-dev libreadline-dev libsqlite3-dev curl git \
    libncursesw5-dev xz-utils tk-dev libxml2-dev libxmlsec1-dev libffi-dev liblzma-dev \
    && wget https://www.python.org/ftp/python/3.13.2/Python-3.13.2.tar.xz \
    && tar -xf Python-3.13.2.tar.xz \
    && cd Python-3.13.2 \
    && ./configure \
    --disable-gil \
    --enable-loadable-sqlite-extensions \
    --enable-optimizations \
    --enable-option-checking=fatal \
    --enable-shared \
    --with-lto \
    --with-ensurepip \
    && make \
    && make install \
    && cd .. \
    && rm -rf Python-3.13.2 \
    && rm Python-3.13.2.tar.xz \
    && apt-get remove -yy \
    build-essential libssl-dev zlib1g-dev \
    libbz2-dev libreadline-dev libsqlite3-dev curl git \
    libncursesw5-dev xz-utils tk-dev libxml2-dev libxmlsec1-dev libffi-dev liblzma-dev \
    && apt-get autoremove -yy \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/* \
    && rm -rf /var/lib/dpkg/*-old \
    && rm -rf /var/cache/apt/archives \
    && rm -rf /var/cache/apt/archives/partial \
    && rm -rf /var/cache/debconf/*-old

CMD ["/usr/local/bin/python3", "-X", "gil=0"]
