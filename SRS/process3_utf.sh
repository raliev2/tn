#!/bin/bash
export ZAM="В объеме доп.согл. № 2"
cat requirements.csv | perl -i -npe "s/\\\key\{[\t ]+\}//g" | perl -i -npe "s/\"\t\"//g" | perl -i -npe "s/1 этап/$ZAM/g" | perl -i -npe "s/\"\t//g" | perl -i -npe "s/\t\"//g" | perl -i -npe "s/\t//g" | perl -i -npe "s/\"\"/\"/g" | perl -i -npe "s/([\t ])\"/\1<</g" | perl -i -npe "s/\"([\t \.\,])/>>\1/g" > a.csv
cat  a.csv | grep "{$ZAM}" | grep -v "Внутри TI" | grep -v "Удалено" | grep -v "Отменено" | grep "На согласование" |perl -i -npe "s/№/\\\No/g" > stage1.tobeagreed.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "Внутри TI" | grep -v "Удалено" | grep -v "Отменено" | grep -v "На согласование" |perl -i -npe "s/№/\\\No/g" > stage1.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "Внутри TI" | grep -v "Удалено" | grep -v "Отменено" | grep "Протокол встречи от 04.04.2014" |perl -i -npe "s/№/\\\No/g" > 20140404.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "Отменено" | grep -v "Удалено" | grep "Внутри TI" |perl -i -npe "s/№/\\\No/g" > insideTI.csv 
cat  a.csv | grep "{$ZAM}" | grep "Удалено" |perl -i -npe "s/№/\\\No/g" > deleted.csv 

cat  a.csv | grep "{2 этап}"  > stage2.csv 
cat stage1.csv  > stage1.utf8.csv
cat stage2.csv  > stage2.utf8.csv
cat stage1.tobeagreed.csv  > stage1tobeagreed.utf8.csv
cat 20140404.csv  > 20140404.utf8.csv
cat insideti.csv  > insideti.utf8.csv
cat deleted.csv  > deleted.utf8.csv
rm stage1.csv
rm stage2.csv
rm stage1.tobeagreed.csv
rm a.csv
