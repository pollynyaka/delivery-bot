FROM tomcat:8.5

ENV BUILD_DIR /tmp/build_dir

RUN rm -rf $BUILD_DIR && mkdir -p $BUILD_DIR && \
    apt-get update && apt-get install -y --no-install-recommends \
    "openjdk-${JAVA_VERSION%%[.~bu-]*}-jdk=$JAVA_DEBIAN_VERSION" \
    maven

COPY src $BUILD_DIR/src
COPY pom.xml $BUILD_DIR

WORKDIR $BUILD_DIR

RUN mvn clean package && \
    cp target/delivery-bot.war $CATALINA_HOME/webapps/

WORKDIR $CATALINA_HOME

RUN rm -rf $BUILD_DIR && \
    apt-get remove -y --purge \
    "openjdk-${JAVA_VERSION%%[.~bu-]*}-jdk=$JAVA_DEBIAN_VERSION" \
    maven && \
    apt-get autoremove -y --purge

COPY conf/telegram_bot.properties $CATALINA_HOME/conf/

ENV BOT_CONFIG_HOME $CATALINA_HOME/conf/telegram_bot.properties
