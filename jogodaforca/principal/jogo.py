#pasta = "funcao\dados2.txt"
pasta1 = "principal\dados3.txt"
                                    
cor = ('\033[m',      # 0 - sem cores
       '\033[0;31m',  # 1 - vermelho
       '\033[0;32m',  # 2 - verde
       '\033[0;33m',  # 3 - amarelo
       '\033[0;34m',  # 4 - azul
       '\033[0;35m',  # 5 - roxo
       '\033[7;30m',  # 6 - branco
       '\033[0;30;42m',  # 7 - verde-fundo

    )

def aplicarCores(frase = " ",tom = 0):
    cor = ('\033[m',         # 0 - sem cores
       '\033[0;31m',  # 1 - vermelho
       '\033[0;32m',  # 2 - verde
       '\033[0;33m',  # 3 - amarelo
       '\033[0;34m',  # 4 - azul
       '\033[0;35m',  # 5 - roxo
       '\033[7;30m' ,     # 6 - branco
       '\033[0;30;42m',  # 7 - verde-fundo 
       '\033[0;30;41m',  # 8 - vermelho-fundo
       '\033[0;30;44m',  # 9 - azul-fundo
       '\033[0;30;43m',  # 10 - amarelo-fundo
    )
    print(f"{cor[tom]}{frase}{cor[0]}")

def copiararq (arquivo,leitor): 
    """
    #parametro arquivo: representa o arquivo limpo a ser aberto
    #parametro leitor: representa o arquivo com os dados a ser modificado ja aberto em formato readlines
    """
    
    try:
        arq = open(arquivo,"rt",encoding="utf-8")
        arq2 = open(leitor,'w',encoding="utf-8")
        #tem que atribuir o arquivo a ser lido a uma variavel
        dados = arq.readlines()
    except:
        print('Erro ao abri a pasta')
    else:
        print(dados)
        for linha in dados:
            nomes = linha.replace(";",'')
            #nomes = nomes.replace("\n",'')
            arq2.write(nomes)            
        arq.close()
        arq2.close()

def enfeite(msg='',enfe='*'):
    print(enfe*30)
    print(f'{msg:^30}')
    print(enfe*30)

#programa principal
from random import randint
from time import sleep

try:
    with open(pasta1,"r", encoding="utf-8") as arquivo:
        dados = arquivo.readlines()
except:
    print("Arquivo não encontrado, confira o caminho do arquivo.")
else:
    print(f'Arquivo {pasta1} Aberto com sucesso.')

acertos = 0
cont = 0
erros = []
confirmados = []
escolha = []
vof = 0
v = 0
enfeite('START!')
sleep(1)

while True:
    random =randint(0,407)
    novo =dados[random].upper().replace(" ","-")
    nome = novo.replace('\n','')

    
    enfeite('DICAS: ','-')
    sleep(0.7)
    if random < 201:
        enfeite(f'Nome de Pessoa','-' )
        sleep(0.7)
    else:
        enfeite(f'Nome de Animal','-') 
        sleep(0.7)
    enfeite(f"A Palavra tem {len(nome)} letras.",'-')
    sleep(2)
    #esse loop for é para preencher a lista com espaços da palavra sorteada.
    for i in range(0,len(nome)):
        escolha.append("_")
    #esta condição é para palavras que tem o traço '-'

    if '-' in nome:
        for c,i in enumerate(nome):
            if "-" == i:
                del escolha[c]
                escolha.insert(c,'-')
                acertos+=1
    #esse loop é para definir a quantidade de jogadas.           
    while True:
       
        while True:
                    
                letra = str(input(f"\n{cor[4]}Digite uma letra:{cor[0]} ")).upper()
                if len(letra) > 1:
                    aplicarCores('Digite apenas uma letra.',1)
                elif str.isnumeric(letra) or not str.isalpha(letra):
                    aplicarCores('Você não digitou uma letra.',1)                
                else:
                    break          
            
               


        #essa condição é pra verificar se a letra digitada esta no nome selecionado
        if letra not in escolha:
            if letra in nome:       
                for c,i in enumerate(nome):
                    #essa condição é para adicionar a letra na posição correta na lista
                    if letra == i:             
                        del escolha[c]
                        escolha.insert(c,letra)
                        acertos +=1

                # defini quando o jogador ganha, completando todas as letras.
                if acertos >= len(nome) :
                    aplicarCores('PARABENS VOCÊ GANHOU!!',2)                    
                    aplicarCores(f'O nome era: ',3)
                    aplicarCores(f"  {nome}",4)
                    print(f"Você tentou {cont}",  "vez"if cont < 2 else f"vezes.")
                    break

            #adiciona as letra erradas a uma lista    
            else:
                if letra not in erros:
                    erros.append(letra)
                else:
                    aplicarCores('Letra ja digitada.',1)
                    cont-= 1 
        else:
            aplicarCores('Letra ja digitada.',1)
            cont-= 1

        #escreve a lista com as letra escolhidas e corretas.
        sleep(1)
        print('-'*len(nome))
        aplicarCores('Acertos: \n',7)
        for i in range(0,len(nome)):
            print(f'{escolha[i]}', end=' ')
        print('\n')
        print('-'*len(nome))
        sleep(0.5)

        #print da lista de erros
        aplicarCores('Erros:',8)
        enfeite(f'{sorted(erros)}','*') 

#opção de palpite, quando chegar a 50% de acertos ou proximo. 
        if acertos >= int(len(nome)/2):
            sleep(0.5)
            while  vof < 1:
                aplicarCores('Você quer arriscar o nome?',9)
                digitar = str(input("[S/N]")).strip().upper()
                if digitar == "S":
                    while True:
                        confirma = str(input("Digite seu palpite: ")).strip().upper()
                        aplicarCores(f"Seu palpite foi: {confirma}",3)  
                        aplicarCores('Esta Correto? ',9)                      
                        con = str(input('[S/N]')).strip().upper()
                        if con in "S":
                            print('Verificando...')
                            sleep(1)
                            if confirma.replace(" ","-") == nome:

                                print(f'{cor[2]}-{cor[0]}'*30)
                                aplicarCores('   Parabens você ganhou!!',2) 
                                print(f'{cor[2]}-{cor[0]}'*30)
                                v += 1 
                                vof += 1                              
                                break    
                            else: 
                                print('Verificando...')
                                sleep(1)                                                               
                                aplicarCores('Você errou.',1)
                                vof += 1
                                break
                                
                        elif con not in "N":
                            print("Digite S/N")
                                
                elif digitar == "N":
                    vof+=1
                else:
                    print('Digite apenas S/N')
            vof-=1 
                                   
        cont+=1
        #frase avisando quantas jogadas falta.
        print('-'*30)
        print(f'Você tentou {cont}', "vez"if cont < 2 else "vezes",end=' ') 
        print(f'ainda falta {10-cont}'if v < 1  else'.')
        print('-'*30)
        if v == 1:
            break
        elif cont == 10:
            print(f'{cor[1]}-{cor[0]}'*30)
            aplicarCores(f'       Você perdeu!!!',1)
            print(f'{cor[1]}-{cor[0]}'*30)
            aplicarCores(f'A palavra era {nome}',3)
            break
        
    #confirmação depois que acaba o jogo
    aplicarCores("JOGAR MAIS UMA VEZ?",9)   
    confir = str(input('S/N' )).strip().upper()
    if confir == 'N':
        break
    # se for continuar zera a base de decisão do jogo
    else:
        escolha.clear()
        erros.clear()
        acertos = 0
        cont = 0
        v = 0

enfeite("FIM DO JOGO",'=')
  