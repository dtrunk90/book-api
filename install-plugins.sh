#!/bin/bash

set -e

echo "[*] Installing Jenkins plugins..."
/usr/bin/jenkins-plugin-cli --verbose --plugin-file /usr/share/jenkins/ref/plugins.txt
