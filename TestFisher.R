data <- read.table("TabellaContingenzaPerFisherTest.txt")

pvalue<-c()

for(i in 1:dim(data)[1]) {
contingencyTable<-matrix(c(data[i,1],data[i,2],data[i,3],data[i,4]), nrow = 2, ncol = 2)
pvalue[i]<-fisher.test(contingencyTable, alternative="greater")$p.value}

pvalue<-matrix(c(pvalue))

GOterm_pvalue<-cbind(row.names(data),pvalue)

write.table(GOterm_pvalue,"FisherTestResults.txt", sep="\t", col.names = c("GOterm", "pvalue"), row.names = FALSE, qmethod = c("escape", "double"))
