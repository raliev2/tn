#!/bin/bash
export ZAM="� ������ ���.����. � 2"
cat requirements.csv | perl -i -npe "s/\\\key\{[\t ]+\}//g" | perl -i -npe "s/\"\t\"//g" | perl -i -npe "s/1 ����/$ZAM/g" | perl -i -npe "s/\"\t//g" | perl -i -npe "s/\t\"//g" | perl -i -npe "s/\t//g" | perl -i -npe "s/\"\"/\"/g" | perl -i -npe "s/([\t ])\"/\1<</g" | perl -i -npe "s/\"([\t \.\,])/>>\1/g" > a.csv
cat  a.csv | grep "{$ZAM}" | grep -v "������ TI" | grep -v "��������" | grep -v "�������" | grep "�� ������������" |perl -i -npe "s/�/\\\No/g" > stage1.tobeagreed.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "������ TI" | grep -v "��������" | grep -v "�������" | grep -v "�� ������������" |perl -i -npe "s/�/\\\No/g" > stage1.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "������ TI" | grep -v "��������" | grep -v "�������" | grep "�������� ������� �� 04.04.2014" |perl -i -npe "s/�/\\\No/g" > 20140404.csv 
cat  a.csv | grep "{$ZAM}" | grep -v "��������" | grep "������ TI" |perl -i -npe "s/�/\\\No/g" > insideTI.csv 
cat  a.csv | grep "{2 ����}"  > stage2.csv 
iconv -f cp1251 -t utf-8 stage1.csv  > stage1.utf8.csv
iconv -f cp1251 -t utf-8 stage2.csv  > stage2.utf8.csv
iconv -f cp1251 -t utf-8 stage1.tobeagreed.csv  > stage1tobeagreed.utf8.csv
iconv -f cp1251 -t utf-8 20140404.csv  > 20140404.utf8.csv
iconv -f cp1251 -t utf-8 insideti.csv  > insideti.utf8.csv
rm stage1.csv
rm stage2.csv
rm stage1.tobeagreed.csv
rm a.csv
