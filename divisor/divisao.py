from random import randint
# 63/9 questões depois falta 5 pra sortear aleatorio.
alunos = ["Lucas Souza","Lucas Guerrero","Jean","Gabriel","Gustavo","Hidekel","Kayme","Isabella","Mauricio"]

lista = [[],[],[],[],[],[],[],[],[]]#lista que os numeros serão adicionados
lista_total = []#lista em que os numeros ja sorteados serão adicionado

#FOR para adicionar os numeros sem ter numeros repetido nas listas
for i in range(0,9):
    contador = 1    
    #while para verificar se hã numeros repetidos e confirmar que existe realmente 7 numeros adicionados
    while (contador <  8):
        aleatorio = randint(1,68)  
        #esse IF verifica se o numero ja não foi sorteado      
        if aleatorio not in lista_total:
            lista[i].append(aleatorio)
            lista_total.append(aleatorio)
            contador += 1
    #print(f"{alunos[i]} --> {sorted(lista[i])}")    



lista2 = []
#esse for é pra sortear os 5 numeros que faltaram dos 68
for i in range(1,len(lista_total)+1):
    if i not in lista_total:
        for add in range(0,1):
            contador = 0
            #esse while é para verificar se o numero do aluno ja foi sorteado e poder adicionar a questão  a lista do aluno.
            while contador < 1:
                a = randint(0,8)
                
                if a not in lista2:
                    lista[a].append(i)
                    lista2.append(a)
                    contador+=1
                   # print(f"n°{i}  {alunos[a]} --> {sorted(lista[a])}")
for c,i in enumerate(lista):
    print(f"{alunos[c]} --> {sorted(i)}")
    
            

        
#print(sorted(lista))
#print("\n")
#print(f"Tamanho de lista é {len(lista_total)}")
#print(sorted(lista_total))
