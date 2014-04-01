ssr.exe -l -r "OOOOdocumentnameOOOO"="stageone" "OOOOfilenameOOOO"="stage1.utf8" TN.SRS.TEMPLATE.tex > TN.SRS.STAGE1.tex
ssr.exe -l -r "OOOOdocumentnameOOOO"="stageonetobeagreed" "OOOOfilenameOOOO"="stage1tobeagreed.utf8"  TN.SRS.TEMPLATE.tex > TN.SRS.STAGE1tobeagreed.tex
ssr.exe -l -r "OOOOdocumentnameOOOO"="stagetwo" "OOOOfilenameOOOO"="stage2.utf8" TN.SRS.TEMPLATE.tex > TN.SRS.STAGE2.tex
pdflatex TN.SRS.STAGE1.tex
pdflatex TN.SRS.STAGE2.tex
pdflatex TN.SRS.STAGE1tobeagreed.tex
makeindex.exe "TN.SRS.STAGE1".idx
makeindex.exe "TN.SRS.STAGE1tobeagreed".idx
makeindex.exe "TN.SRS.STAGE2".idx
pdflatex TN.SRS.STAGE1.tex
pdflatex TN.SRS.STAGE2.tex
pdflatex TN.SRS.STAGE1tobeagreed.tex
move TN.SRS.STAGE1.pdf pdf\
move TN.SRS.STAGE2.pdf pdf\
move TN.SRS.STAGE1tobeagreed.pdf pdf\

