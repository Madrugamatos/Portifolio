#pasta = "funcao\dados2.txt"
pasta1 = "dados3.txt"
                                    
cor = ('\033[m',      # 0 - sem cores
       '\033[0;31m',  # 1 - vermelho
       '\033[0;32m',  # 2 - verde
       '\033[0;33m',  # 3 - amarelo
       '\033[0;34m',  # 4 - azul
       '\033[0;35m',  # 5 - roxo
       '\033[7;30m',  # 6 - branco
       '\033[0;30;42m',  # 7 - verde-fundo

    )

#essa função foi criada para formatar os nomes e pode tirar acentos espaços e copiar para um novo arquivo
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
try:
    while True:
        try:
            random =randint(0,407)
            novo =dados[random].upper().replace(" ","-")
            nome = novo.replace('\n','')
        except:
            print("""Verifique o caminho do arquivo "dados" na linha 2.\nVeja se o arquivo encontrase dentro da\nmesma pasta e se o caminho esta correto.
                  """)
            break

        
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
                        
                    letra = str(input(f"\nDigite uma letra:")).upper()
                    if len(letra) > 1:
                        print('Digite apenas uma letra.')
                    elif str.isnumeric(letra) or not str.isalpha(letra):
                       print('Você não digitou uma letra.')                
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
                        print('PARABENS VOCÊ GANHOU!!')                    
                        print(f'O nome era: ')
                        print(f"  {nome}")
                        print(f"Você tentou {cont}",  "vez"if cont < 2 else f"vezes.")
                        break

                #adiciona as letra erradas a uma lista    
                else:
                    if letra not in erros:
                        erros.append(letra)
                    else:
                        print('Letra ja digitada.')
                        cont-= 1 
            else:
                print('Letra ja digitada.')
                cont-= 1

            #escreve a lista com as letra escolhidas e corretas.
            sleep(1)
            print('-'*len(nome))
            print('Acertos: \n')
            for i in range(0,len(nome)):
                print(f'{escolha[i]}', end=' ')
            print('\n')
            print('-'*len(nome))
            sleep(0.5)

            #print da lista de erros
            print('Erros:')
            enfeite(f'{sorted(erros)}','*') 

    #opção de palpite, quando chegar a 50% de acertos ou proximo. 
            if acertos >= int(len(nome)/2):
                sleep(0.5)
                while  vof < 1:
                    print('Você quer arriscar o nome?')
                    digitar = str(input("[S/N]")).strip().upper()
                    if digitar == "S":
                        while True:
                            confirma = str(input("Digite seu palpite: ")).strip().upper()
                            print(f"Seu palpite foi: {confirma}")  
                            print('Esta Correto? ')                      
                            con = str(input('[S/N]')).strip().upper()
                            if con in "S":
                                
                                if confirma.replace(" ","-") == nome:
                                    print('Verificando...')
                                    sleep(1)
                                    print(f'-'*30)
                                    print('   Parabens você ganhou!!') 
                                    print(f'-'*30)
                                    print(f'O nome era: ')
                                    print(f"  {nome}")
                                    v += 1 
                                    vof += 1                              
                                    break    
                                else: 
                                    print('Verificando...')
                                    sleep(1)                                                               
                                    print('Você errou.')
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
            print(f'falta {10-cont}'if v < 1  else'.')
            print('-'*30)
            #finaliza o programa caso acerte o palpite
            if v == 1:
                break
            elif cont == 10:
                print(f'-'*30)
                print(f'       Você perdeu!!!')
                print(f'-'*30)
                print(f'A palavra era {nome}')
                break
            
        #confirmação depois que acaba o jogo
        print("JOGAR MAIS UMA VEZ?")   
        confir = str(input('S/N' )).strip().upper()
        if confir == 'N':
            break
        # se for continuar zera a base de decisão do jogo
        #v é usado em vitoria por palpite digitado.
        else:
            escolha.clear()
            erros.clear()
            acertos = 0
            cont = 0
            v = 0
except KeyboardInterrupt:
    print('Jogador saiu do jogo.')
enfeite("FIM DO JOGO",'=')
  