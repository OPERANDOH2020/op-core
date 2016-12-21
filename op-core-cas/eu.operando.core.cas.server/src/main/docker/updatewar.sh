#!/bin/bash -e

cd /etc/cas
jar -uvf /cas-overlay/target/cas.war WEB-INF
