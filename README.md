Pelada De Quinta
==============

Aplicativo ANDROID para randomizar equipes de futebol (pelada) equilibradas pela qualidade dos jogadores.

Para Versões 4.0+.

------------------------------------------------------------------------------------------------------------------------
Jogadores - Table: players
------------------------------------------------------------------------------------------------------------------------
Campos: ID, name, quality, isgoalkeeper, status;



ID (integer)              - Número identificador de cada jogador, autoincrement

name (string)             - Nome do jogador

quality (integer)         - nível de cada jogador, sendo 3 níveis, definidos pela quantidade de estrelas:

                            1 - Regular
                            2 - Bom
                            3 - Excelente
                            
isgoalkeeper (boolean)    -  define se o jogador é goleiro ou não.

status (boolean)          - define se o jogador está presente nesta partida ou não.


------------------------------------------------------------------------------------------------------------------------
Histórico - Table: history
------------------------------------------------------------------------------------------------------------------------
Campos: matchdate, firstteam, secondteam


matchdate (datetime)      - Data da partida

firstteam (string)        - Jogadores do primeiro time

secondteam (string)       - Jogadores do segundo time



--------
Desenvolvido por Giorgio Fellipe
