==============================================================================
                    ARENA DOS CAMPEÕES  -  EDIÇÃO EVANGELION
                      Batalha em turnos no terminal (Java)
==============================================================================

Desafio Final - Back-end: Java e Orientação a Objetos (Teach 3035)
Autor(a): Talita de Souza Keller

Link do vídeo no Youtube:  https://www.youtube.com/watch?v=_KvjZ0hNfBE


SUMÁRIO
-------
 1. Sobre o jogo
 2. Requisitos técnicos atendidos
 3. Como executar
 4. Como jogar
 5. A Loja
 6. Pontuação e Ranking
 7. Os Pilotos
 8. Os Anjos
 9. Dicas rápidas
10. Estrutura do projeto
11. Créditos


==============================================================================
1. SOBRE O JOGO
==============================================================================

Arena dos Campeões é um RPG de batalha por turnos que roda inteiro no
terminal, inspirado no anime Neon Genesis Evangelion.

Você escolhe um piloto da NERV (Rei, Shinji ou Asuka), entra no seu Eva e
enfrenta uma sequência de 10 batalhas contra os Anjos, os misteriosos
inimigos que atacam Tokyo-3. A cada turno você decide: atacar, defender,
usar o ataque especial do seu piloto ou tomar uma poção. Entre uma batalha e
outra, você pode gastar as moedas que ganhou na Loja para ficar mais forte.

Cada Anjo tem personalidade própria, com habilidade especial e jeito de lutar
inspirados no que ele faz no anime. Conhecer o inimigo faz diferença!

Observação sobre o enunciado: o desafio pede "uma classe de personagem" entre
Guerreiro, Mago e Arqueiro. Aqui cada classe virou um piloto do anime:

    Guerreiro  ->  Shinji Ikari   (Eva-01)
    Mago       ->  Rei Ayanami    (Eva-00)
    Arqueiro   ->  Asuka Langley  (Eva-02)

O "nome do jogador" é pedido no final da partida, no estilo arcade (4 letras),
para entrar no Ranking.


==============================================================================
2. REQUISITOS TÉCNICOS ATENDIDOS
==============================================================================

1) Estrutura de classes
   - Personagem (classe base abstrata): nome, vida, ataque, defesa e
     vidaMaxima, todos privados e acessados por getters/setters
     (encapsulamento).
     Métodos: atacar(Personagem alvo), defender(), tomarDano(int dano) e
     estaVivo(). Também tem curar(), tomarDanoDireto() (dano que ignora a
     defesa) e os controles de efeitos de status.
   - Inventário: foi usada a opção "contagem simples". A classe Piloto guarda
     o número de poções (começa com 3) e o número de usos do ataque especial.
   - Subclasses de herói: Piloto (abstrata, estende Personagem) e, a partir
     dela, Shinji (Guerreiro), Rei (Mago) e Asuka (Arqueiro). Cada uma
     implementa o próprio ataque especial (polimorfismo via usarEspecial()).
   - Subclasses de inimigo: Inimigo (estende Personagem) e uma subclasse para
     cada Anjo, cada uma sobrescrevendo o comportamento do turno (agir())
     para criar sua habilidade especial.
   - Enum Acao (ATACAR / DEFENDER), usado pela IA dos inimigos.

2) Sistema de batalha por turnos
   - A cada turno são exibidos a vida dos dois lados e o menu de ações.
   - Ações: Atacar, Defender, Ataque Especial, Usar Poção de Cura (restaura
     20 de vida, 3 por partida) e Ver estatísticas (não gasta o turno).
   - IA simples do inimigo: cada Anjo tem duas arrays de ações (uma para
     quando está com mais da metade da vida e outra para quando está ferido).
     A cada turno ele sorteia uma ação do array, então uns atacam sempre,
     outros defendem com mais frequência.
   - Entrada do jogador lida com Scanner.

3) Inimigos aleatórios
   - A classe FabricaInimigos cria os Anjos, cada um com nome, atributos,
     arte ASCII e descrição próprios.
   - Os 9 primeiros inimigos são sorteados dentro de três faixas de
     dificuldade (batalhas 1-3, 4-6 e 7-9), então toda partida é diferente.
     O chefe final é sempre o último.

4) Controle de estado do jogo
   - O jogo termina quando o piloto morre ou quando todos os oponentes da
     arena são derrotados, com mensagem de vitória ou de derrota.
   - São controlados: número da batalha ("BATALHA X de 10"), vida atual e
     máxima, poções restantes, usos do especial, moedas e pontos.

5) Modularização
   - Main.java divide o fluxo em métodos: iniciarJogo(), exibirStatus(),
     turnoDoPiloto() (o "turnoDoJogador" do enunciado), turnoDoInimigo() e
     fimDeJogo(), além de lutar(), escolherPiloto(), mostrarMenuAcoes() etc.
   - Textos e artes ASCII ficam isolados em utils/DadosAnjos.

6) Tratamento de erros
   - Todas as entradas são validadas: se digitar letra onde era número, o
     jogo avisa e pede de novo; número fora das opções mostra "Opção
     inválida!"; perguntas de S/N e o nome de 4 letras também são validadas.
     Nada disso derruba o programa.
   - Ação impossível (sem poção, vida cheia, sem usos do especial) não gasta
     o seu turno: o jogo avisa e deixa você escolher outra.

Bônus implementados
   - Sistema de pontuação (veja a seção 6).
   - Ranking dos 10 melhores (lista de Registro ordenada por pontuação).
   - Loja entre as batalhas, com melhorias pagas com moedas (seção 5).
   - Tela de seleção de personagens com descrição de cada piloto.
   - Projeto organizado em pacotes (entities, entities.pilotos,
     entities.inimigos e utils).


==============================================================================
3. COMO EXECUTAR
==============================================================================

Requisito: Java 15 ou superior (o projeto usa text blocks).

Abra o terminal DENTRO da pasta "java" (a que contém o Main.java) e rode:

  Compilar:
    javac -encoding UTF-8 -d out Main.java entities/*.java entities/pilotos/*.java entities/inimigos/*.java utils/*.java

  Executar:
    java -cp out Main

Pela IDE (IntelliJ, Eclipse, VS Code): abra a pasta do projeto e execute a
classe Main.


Dica: use um terminal com a janela bem larga, porque as artes ASCII dos
Anjos são grandes.


==============================================================================
4. COMO JOGAR
==============================================================================

4.1 Começando
-------------
O jogo mostra os três pilotos, cada um com uma descrição. Digite o número do
piloto escolhido:

    1 - Rei Ayanami
    2 - Shinji Ikari
    3 - Asuka Langley Soryu

Em seguida aparecem os atributos iniciais do piloto escolhido.

4.2 A arena
-----------
A arena tem 10 batalhas, uma contra cada inimigo. A tela mostra o contador
("BATALHA 3 de 10"), a arte ASCII do Anjo, o nome e uma descrição dele.
A batalha é em turnos alternados (você, depois o inimigo) e só termina quando
um dos dois cai. Se o seu piloto cair, o jogo acaba. Se vencer, você segue
para a próxima batalha, com a mesma vida que terminou (mais as recuperações
descritas em 4.7).

4.3 O seu turno
---------------
No começo do turno aparece a vida dos dois lados e o menu:

    1 - Atacar              Golpe normal, causa dano igual ao seu ataque.
    2 - Defender            Reduz o dano do próximo golpe que você receber.
    3 - Ataque Especial     Golpe único de cada piloto, com usos limitados.
    4 - Usar Poção          Recupera 20 de vida. Você começa com 3 poções.
    5 - Ver estatísticas    Mostra os números completos dos dois lados.

A opção 5 não gasta o turno: serve para você estudar o inimigo. Ela mostra a
vida, o ataque e a defesa dele, a chance de ele se defender, o estilo de luta
e a habilidade especial.

As opções 3 e 4 não gastam o turno quando não podem ser usadas (sem usos
restantes, sem poções ou, no caso da poção, com a vida já cheia). O jogo
avisa e você escolhe outra ação.

4.4 Como o dano funciona
------------------------
  - Golpe normal sem defesa: o alvo leva o ataque inteiro do atacante.
  - Defendendo: o alvo leva (ataque do atacante - defesa do alvo), no mínimo
    1. Ou seja, o atributo Defesa só entra em ação quando você usa a ação
    Defender.
  - A guarda dura até o começo do turno seguinte de quem defendeu, então
    protege contra o próximo golpe do adversário.
  - Dano direto: alguns golpes ignoram a defesa (o Ataque Infalível da Rei,
    o dano contínuo e o aperto inicial de Bardiel).
  - Vida nunca passa do máximo, e poções e curas respeitam esse limite.
  - Os inimigos também podem defender: atacar um Anjo em guarda rende menos
    dano.

4.5 O turno do inimigo
----------------------
O Anjo sorteia entre atacar e defender (a chance muda conforme a vida dele,
veja a opção 5) e, além disso, usa a habilidade especial própria nos
momentos certos (a cada 2 ou 3 turnos, ao chegar na metade da vida etc.).
Está tudo descrito na seção 8.

4.6 Efeitos especiais
---------------------
  - Dano contínuo: causa dano no começo de cada um dos seus próximos turnos,
    ignorando a defesa.
  - Preso: você perde a vez no próximo turno.
Os efeitos são limpos quando uma nova batalha começa.

4.7 Entre as batalhas
---------------------
  - Descanso: ao começar as batalhas 4, 7 e 10, você recupera metade da sua
    vida máxima. Antes da batalha 10 há ainda um bônus extra de 15 de vida.
  - Moedas: cada vitória nas batalhas 1 a 9 rende 1 moeda (no máximo 9 por
    partida).
  - Loja: com 2 ou mais moedas, o jogo pergunta se quer entrar na Loja antes
    de cada batalha (veja a seção 5).

4.8 Fim de jogo
---------------
Ao final aparece a mensagem de vitória ou de derrota e a sua pontuação. Você
digita 4 letras como nome, o Ranking é exibido e o jogo pergunta se quer jogar
de novo (S/N).


==============================================================================
5. A LOJA
==============================================================================

Quando você tem 2 moedas ou mais, antes de cada batalha o jogo pergunta
"Quer entrar na loja? (S/N)". Dentro da Loja você pode comprar quantos itens
quiser, enquanto tiver moedas. Digite 0 para sair. Moedas que sobrarem são
guardadas para a próxima vez.

  Nº  Item                                 Custo
  --  -----------------------------------  ---------
   1  +5 de ataque                         3 moedas
   2  +10 de defesa                        6 moedas
   3  +3 de defesa                         2 moedas
   4  +20 de vida máxima                   6 moedas
   5  Recuperar toda a vida                6 moedas
   6  +1 poção                             2 moedas
   7  +1 uso do ataque especial            3 moedas

Detalhes:
  - O cabeçalho da Loja mostra suas moedas e seus atributos atuais.
  - "+20 de vida máxima" também aumenta a sua vida atual em 20.
  - "Recuperar toda a vida" não pode ser comprado com a vida já cheia.
  - O item 7 vale para o ataque especial do seu piloto (Ataque Infalível,
    Ataque Forte ou Ataque Duplo).
  - Moedas insuficientes? O jogo avisa e nada é cobrado.
  - As moedas zeram quando você começa uma nova partida.

==============================================================================
6. PONTUAÇÃO E RANKING
==============================================================================

Cada batalha vencida dá pontos, e quanto mais perto do final, mais vale:

    Batalhas 1 a 3   ->   5 pontos cada
    Batalhas 4 a 6   ->  10 pontos cada
    Batalhas 7 a 9   ->  15 pontos cada
    Batalha 10       ->  20 pontos

Pontuação máxima: 110 pontos.

Ao final da partida, vitória ou derrota, você digita um nome de exatamente 4
letras. O Ranking mostra os 10 melhores resultados, com nome, pontos e o
piloto usado, em ordem decrescente. O Ranking fica na memória: ele vale
enquanto o programa estiver aberto (inclusive ao jogar de novo) e reinicia
quando o jogo é fechado.


==============================================================================
7. OS PILOTOS
==============================================================================

No anime, os pilotos dos Evas são chamados de "Crianças" (Children), escolhidas
pela NERV para operar as máquinas, porque só elas conseguem sincronizar com os
Evas. Cada piloto tem um perfil de combate inspirado em quem ele é na série.

------------------------------------------------------------------------------
REI AYANAMI  -  EVA-00                            Classe do desafio: Mago
------------------------------------------------------------------------------
No anime:
  Rei é a Primeira Criança (First Child) e pilota o Eva-00, o protótipo da
  NERV. Tem poucas palavras, quase nenhuma expressão, e uma lealdade enorme ao
  comandante Gendo Ikari. Cumpre cada missão sem hesitar, mesmo quando o custo
  é pessoal. Um dos momentos mais marcantes dela é a batalha contra Ramiel
  (Operação Yashima): enquanto Shinji mira o rifle de pósitrons, é Rei quem
  segura o escudo e fica na frente dele, aguentando o raio do Anjo. Ao longo
  da série, ela vai se abrindo aos poucos, principalmente com Shinji.

No jogo:
  Vida 100  |  Ataque 17  |  Defesa 3
  Ataque Infalível (5 usos): causa 22 de dano (ataque + 5) ignorando a
  defesa do alvo. Não importa como o inimigo se proteja, o golpe dela chega.
  (Exceção: Gaghiel submerso não pode ser atingido por nada.)
  Perfil: equilibrada. O sangue-frio dela virou estabilidade, com atributos
  intermediários e um especial confiável contra inimigos de guarda alta.

------------------------------------------------------------------------------
SHINJI IKARI  -  EVA-01                         Classe do desafio: Guerreiro
------------------------------------------------------------------------------
No anime:
  Shinji é a Terceira Criança (Third Child), um garoto de 14 anos chamado a
  Tokyo-3 pelo pai, Gendo, que ele não via havia anos, e jogado de cara dentro
  do Eva-01 para enfrentar Sachiel. Inseguro e com medo, ele vive repetindo
  para si mesmo "eu não devo fugir". Mesmo assim, é quem mais aguenta apanhar
  e quem mais enfrenta Anjos ao longo da série. O Eva-01 também é conhecido por
  "despertar" sozinho e ultrapassar os próprios limites quando o piloto está
  encurralado, como acontece nas lutas contra Sachiel e Leliel.

No jogo:
  Vida 120  |  Ataque 14  |  Defesa 8
  Ataque Forte (3 usos): causa 32 de dano (ataque + 18), mas sacrifica 10 da
  sua própria vida (nunca te deixa com menos de 1).
  Perfil: tanque. Maior vida e maior defesa do trio, com o menor ataque.
  O Ataque Forte nasce do desespero: ele se machuca para bater mais forte,
  como o Eva-01 quando ultrapassa os limites.

------------------------------------------------------------------------------
ASUKA LANGLEY SORYU  -  EVA-02                   Classe do desafio: Arqueiro
------------------------------------------------------------------------------
No anime:
  Asuka é a Segunda Criança (Second Child), pilota do Eva-02, o Eva vermelho.
  Orgulhosa, competitiva e confiante, foi treinada desde criança para ser a
  melhor piloto de todas, e não admite perder nem para Shinji. Ela chega ao
  Japão de navio, trazendo o Eva-02, e logo na viagem já precisa enfrentar
  Gaghiel. Na luta contra Israfel, o Anjo gêmeo, ela e Shinji só vencem
  atacando em perfeita sincronia, o que obriga a dupla a treinar junta.

No jogo:
  Vida 80  |  Ataque 22  |  Defesa 5
  Ataque Duplo (4 usos): 50% de chance de causar 44 de dano (o dobro do
  ataque). Se falhar, sobra um tiro normal de 22.
  Perfil: canhão de vidro. O maior ataque do grupo, mas a menor vida. O estilo
  arriscado do Ataque Duplo lembra a ousadia dela e a luta contra Israfel.


==============================================================================
8. OS ANJOS
==============================================================================

No anime, os Anjos são criaturas gigantes que atacam a Terra, uma
após a outra, sempre rumo ao mesmo destino: o fundo da base da NERV. Cada um
tem forma, poder e jeito de atacar totalmente diferentes, e quase todos
protegem o próprio corpo com o AT Field, uma barreira invisível. Os Evas
são as únicas máquinas capazes de ferir um Anjo.

No jogo, os 9 primeiros Anjos são sorteados em três faixas de dificuldade, e
o chefe final aparece sempre por último. As chances de defesa de cada Anjo
aparecem na opção "5 - Ver estatísticas" durante a batalha.

Legenda dos atributos: Vida / Ataque / Defesa. "Defende" mostra a chance de
ele usar a ação Defender: com a vida alta / com a vida abaixo da metade.

*** FAIXA 1 - Batalhas 1 a 3 (ordem sorteada) ***

------------------------------------------------------------------------------
SACHIEL  -  O Terceiro Anjo
------------------------------------------------------------------------------
No anime:
  O primeiro Anjo a atacar Tokyo-3 e o primeiro inimigo que Shinji enfrenta,
  dentro de um Eva-01 que ele mal sabia pilotar. Avança sobre a cidade sem
  estratégia nenhuma, disparando lanças de luz, e é o Eva-01, despertando em
  fúria, que acaba com ele. No final, ele se autodestrói tentando levar o Eva
  junto.
No jogo:
  30 / 6 / 1  |  Defende: 0% / 0%  (só ataca, o tempo todo)
  Habilidade - AUTODESTRUIÇÃO: quando a vida dele cai para a metade ou
  menos, ele explode no turno seguinte, causando 24 de dano (4x o ataque) em
  você, e morre junto. Defender nesse turno reduz o dano da explosão.

------------------------------------------------------------------------------
RAMIEL  -  O Quinto Anjo
------------------------------------------------------------------------------
No anime:
  Um octaedro azul que paira sobre Tokyo-3 e abre um buraco no teto da NERV com
  um furador de energia, tentando chegar ao fundo da base. Seu AT Field é quase
  impenetrável, e o raio de partículas dele destrói tudo em alcance. Só é
  derrotado na Operação Yashima, um ataque à distância com o rifle de pósitrons
  alimentado por toda a energia elétrica do Japão.
No jogo:
  60 / 7 / 1  |  Defende: 20% / 60%  (se fecha atrás do AT Field)
  Habilidade - RAIO DE PARTÍCULAS: no segundo ataque que ele faz, dispara o
  raio, que causa 22 de dano (ataque + 15). No turno seguinte ele fica
  recarregando e não age, e a guarda dele fica aberta.

------------------------------------------------------------------------------
GAGHIEL  -  O Sexto Anjo
------------------------------------------------------------------------------
No anime:
  Emerge do mar em meio à frota da ONU e ataca os navios que levavam o Eva-02
  até o Japão. Rápido e traiçoeiro, um Anjo marinho que se move como peixe e
  só se mostra na hora certa de atacar. É a primeira luta de Asuka no anime.
No jogo:
  40 / 8 / 2  |  Defende: 20% / 40%  (oportunista)
  Habilidade - MERGULHO: ao chegar à metade da vida (só uma vez), ele submerge
  por um turno sem atacar e sem poder ser atingido por nada, nem pelo Ataque
  Infalível da Rei. Volta curado em 25% da vida total (+10) e age normalmente.

*** FAIXA 2 - Batalhas 4 a 6 (ordem sorteada) ***

------------------------------------------------------------------------------
ISRAFEL  -  O Sétimo Anjo
------------------------------------------------------------------------------
No anime:
  O Anjo gêmeo. Quando ferido, ele se divide em dois corpos que atacam em
  perfeita sincronia, a caminho da NERV. A única forma de vencê-lo é acertar os
  dois ao mesmo tempo, e para isso Shinji e Asuka precisam treinar movimentos
  em uníssono, algo que nenhum dos dois queria fazer.
No jogo:
  55 / 9 / 3  |  Defende: 20% / 20%  (frieza constante, nenhum golpe o faz
  recuar)
  Habilidade - ATAQUE SINCRONIZADO: sempre que ele decide atacar, tem 2/3 de
  chance de golpear duas vezes seguidas ("Kou e Otsu atacam em sincronia!").
  Defender é ótimo contra ele, porque cada golpe do par é reduzido pela sua
  defesa.

------------------------------------------------------------------------------
MATARAEL  -  O Nono Anjo
------------------------------------------------------------------------------
No anime:
  Desce sobre a NERV com pernas de aranha e tenta dissolver o teto da base por
  cima, com ácido. Luta como um bicho ferido, que não recua nem pensa em
  defesa, só continua avançando.
No jogo:
  60 / 11 / 3  |  Defende: 0% / 0%  (nunca se defende)
  Habilidade - CHUVA CORROSIVA: a cada 3 turnos, em vez de atacar, joga ácido
  que queima você por 2 turnos seguidos (8 de dano por turno, ignorando a
  defesa).

------------------------------------------------------------------------------
SAHAQUIEL  -  O Décimo Anjo
------------------------------------------------------------------------------
No anime:
  Vigia da órbita, calculou a própria queda para cair direto sobre o quartel da
  NERV. Os três Evas, de Shinji, Rei e Asuka, precisam ir juntos segurar o
  Anjo antes do impacto, usando os próprios AT Fields. É um dos poucos momentos
  em que o trio luta lado a lado.
No jogo:
  65 / 12 / 4  |  Defende: 40% / 60%  (calculista, sabe quando atacar e
  quando se proteger)
  Habilidade - QUEDA ORBITAL: a cada 3 turnos ele se joga sobre você com 22 de
  dano (ataque + 10), mas o impacto também o machuca (8 de dano nele).

*** FAIXA 3 - Batalhas 7 a 9 (ordem sorteada) ***

------------------------------------------------------------------------------
LELIEL  -  O Décimo Segundo Anjo
------------------------------------------------------------------------------
No anime:
  Uma sombra gigante que paira sobre a cidade e engole o Eva-01 inteiro, com
  Shinji lá dentro, em um espaço escuro chamado Mar de Dirac. De dentro dessa
  escuridão, o Eva-01 desperta em fúria e rasga o Anjo por dentro, em uma das
  cenas mais lembradas da série.
No jogo:
  80 / 12 / 4  |  Defende: 0% / 20%  (descarrega a fúria sem se proteger)
  Habilidade - MAR DE DIRAC: a cada 3 turnos ele engole você com um dano leve
  (metade do ataque dele, 6) e você perde o próximo turno. Depois, a sombra se
  rasga e a luta continua.

------------------------------------------------------------------------------
BARDIEL  -  O Décimo Terceiro Anjo
------------------------------------------------------------------------------
No anime:
  Não veio de fora: tomou o corpo do Eva-03, que era pilotado por Toji
  Suzuhara, colega de turma de Shinji. Uma máquina aliada vira uma ameaça
  dentro da própria NERV, e Shinji precisa enfrentá-la sem saber que, dentro
  dela, está alguém que ele conhece. É um dos momentos mais pesados da série.
  Bardiel ataca o Eva-01 com golpes de estrangulamento.
No jogo:
  90 / 13 / 5  |  Defende: 20% / 40%  (aguenta o castigo)
  Habilidade - ESTRANGULAMENTO: a cada 3 turnos ele agarra o seu pescoço, com 9
  de dano direto, e continua apertando por mais 2 turnos (9 de dano por turno).
  Tudo isso ignora a sua defesa.

------------------------------------------------------------------------------
KAWORU  -  O Décimo Sétimo Anjo
------------------------------------------------------------------------------
No anime:
  Chega sem pressa, com ar de garoto comum, cabelos prateados e olhos
  vermelhos. Apresenta-se como o Quinto Piloto, se aproxima de Shinji e se
  torna o primeiro a aceitá-lo exatamente como ele é. Mas Kaworu é um Anjo, e
  desce até o fundo da NERV atrás do que acredita ser Adão. É o inimigo que
  menos parece querer lutar, e o encontro com ele é menos uma batalha e mais
  uma decisão que cabe a Shinji.
No jogo:
  100 / 11 / 8  |  Defende: 40% / 60%  (calmo e difícil de acertar)
  Habilidade - AT FIELD: depois de 2 ataques, levanta uma barreira que reduz
  em 10 o dano dos seus próximos 2 golpes (mínimo 1). Ele tem a maior defesa
  de todos. O Ataque Infalível da Rei atravessa a barreira.

*** CHEFE FINAL - Batalha 10 ***

------------------------------------------------------------------------------
LILITH
------------------------------------------------------------------------------
No anime:
  No fundo do Terminal Dogma, o nível mais profundo da NERV, está uma gigante
  branca, crucificada, com uma lança cravada no peito. Todos os Anjos atacaram
  a cidade em direção a ela. Mas quase nada sobre ela é explicado com clareza,
  e o que a NERV guarda ali embaixo tem mais segredos do que aparenta. Quem
  chegar até ela vai descobrir.
No jogo:
  150 / 16 / 6  |  Defende: 20% / 40%  (equilibrada, sem nenhum extremo)
  Habilidade - REGENERAÇÃO: a cada 2 turnos, se estiver ferida, em vez de
  atacar ela recupera 10% da vida máxima (15). Quanto mais a luta se arrasta,
  mais ela se recupera.
  Maior vida, maior ataque e defesa alta: é o teste final de tudo o que você
  construiu até aqui.


==============================================================================
9. DICAS RÁPIDAS
==============================================================================

  - Use "5 - Ver estatísticas" sempre que enfrentar um Anjo novo. Não gasta
    turno e mostra o estilo e a habilidade dele.
  - Defender só vale quando você sabe que o golpe forte está chegando. Muitos
    Anjos têm habilidade a cada 2 ou 3 turnos: conte os turnos!
  - Dano contínuo (Matarael, Bardiel) ignora defesa. Aí o que ajuda é ter
    vida de sobra e poção na manga.
  - Sachiel explode quando chega à metade da vida: defenda nesse turno.
  - Com Gaghiel submerso, não gaste o seu especial, porque ele não pode ser
    atingido.
  - O Ataque Infalível da Rei é a melhor arma contra inimigos de guarda alta,
    como Kaworu.
  - Poções são limitadas (3 por partida). Poupe-as para as batalhas finais e
    aproveite as recuperações automáticas das batalhas 4, 7 e 10.


==============================================================================
10. ESTRUTURA DO PROJETO
==============================================================================

java/
|-- Main.java ................ fluxo do jogo, menus, turnos e pontuação
|-- entities/
|   |-- Personagem.java ...... classe base abstrata (vida, ataque, defesa...)
|   |-- Piloto.java .......... base abstrata dos heróis (poções e especial)
|   |-- Inimigo.java ......... base dos Anjos (IA por tabelas de ação)
|   |-- Acao.java ............ enum ATACAR / DEFENDER
|   |-- pilotos/ ............. Rei, Shinji e Asuka
|   `-- inimigos/ ............ um arquivo para cada Anjo, de Sachiel a Lilith
`-- utils/
    |-- FabricaInimigos.java . cria e sorteia os inimigos
    |-- Loja.java ............ loja entre as batalhas
    |-- Ranking.java ......... Top 10
    |-- Registro.java ........ um registro do ranking (nome, piloto, pontos)
    `-- DadosAnjos.java ...... textos e artes ASCII dos Anjos


==============================================================================
11. CRÉDITOS
==============================================================================

Projeto feito por fã, sem fins lucrativos, com fins educacionais, como
entrega do Desafio Final do módulo de Java e Orientação a Objetos.

Neon Genesis Evangelion e seus personagens pertencem a seus criadores e
detentores de direitos (Hideaki Anno / Gainax / Khara).
