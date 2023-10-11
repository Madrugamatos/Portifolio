from random import randint
# 63/9 questões depois falta 5 pra sortear aleatorio.
alunos = ["Lucas Souza","Lucas Guerrero","Jean","Gabriel","Gustavo","Hidekel","Kayme","Isabella","Mauricio"]

lista = [[],[],[],[],[],[],[],[],[]]
lista_total = []
print(len(lista[0]))
for i in range(0,9):
    contador = 1
    
    while (contador <  8):
        aleatorio = randint(1,68)        
        if aleatorio not in lista_total:
            lista[i].append(aleatorio)
            lista_total.append(aleatorio)
            contador += 1
    print(f"{alunos[i]} --> {sorted(lista[i])}")    



lista2 = []
for i in range(1,len(lista_total)+1):
    if i not in lista_total:
        for add in range(0,1):
            contador = 0
            while contador < 1:
                a = randint(0,8)
                
                if a not in lista2:
                    lista[a].append(i)
                    lista2.append(a)
                    contador+=1
                    print(f"n°{i}  {alunos[a]} --> {sorted(lista[a])}")
#for i in lista:
 #   print(f"{i}")
            

        
#print(sorted(lista))
#print("\n")
#print(f"Tamanho de lista é {len(lista_total)}")
#print(sorted(lista_total))
