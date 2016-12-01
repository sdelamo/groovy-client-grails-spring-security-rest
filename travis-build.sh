#!/usr/bin/env bash
set -e
echo "TRAVIS_TAG          : $TRAVIS_TAG"
echo "TRAVIS_BRANCH       : $TRAVIS_BRANCH"
echo "TRAVIS_PULL_REQUEST : $TRAVIS_PULL_REQUEST"
echo "Publishing archives for branch $TRAVIS_BRANCH"

./gradlew clean check --stacktrace

EXIT_STATUS=0

if [[ -n ${TRAVIS_TAG} ]] || [[ ${TRAVIS_BRANCH} == 'master' && ${TRAVIS_PULL_REQUEST} == 'false' ]]; then
  if [[ -n ${TRAVIS_TAG} ]]; then
    echo "Pushing build to Bintray"
    ./gradlew release -PpluginVersion=${TRAVIS_TAG}  || EXIT_STATUS=$?
  fi
fi
exit ${EXIT_STATUS}