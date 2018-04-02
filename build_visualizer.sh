#!/bin/bash

VISUALIZER_REPO=https://github.com/SemaiCZE/perf-data-visualizer.git
TEMP_DIR=/tmp/perf-data-visualizer
RESOURCE_DIR=./src/main/resources/cz/cuni/mff/d3s/spl/visualization

echo -e "\nClonning visualizer to ${TEMP_DIR}"
git clone ${VISUALIZER_REPO} ${TEMP_DIR}

echo -e "\nChanging working directory to ${TEMP_DIR}"
pushd ${TEMP_DIR}

echo -e "\nInstalling dependecies"
yarn install

echo -e "\nBuilding production build"
yarn build

echo -e "\nChanging working directory back"
popd

echo -e "\nCopying production build files for resources directory ${RESOURCE_DIR}"
cp -r ${TEMP_DIR}/build/* ${RESOURCE_DIR}

echo -e "\nCleaning temporary directory ${TEMP_DIR}"
rm -rf ${TEMP_DIR}

echo -e "\nSuccess"

