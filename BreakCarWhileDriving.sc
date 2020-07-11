SCRIPT_START
{
NOP

LVAR_INT scplayer plcar rndnum rndwait generated carfire    //Agradecimentos ao _jakez pela dica

generated = 0                                               //PRA EVITAR DE O COMANDO FICAR SE REPETINDO,DEVE TER OUTRO MÉTODO MAS EU N LEMBRO

GET_PLAYER_CHAR 0 scplayer

main_loop:
WAIT 0
IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
    GET_CAR_CHAR_IS_USING scplayer plcar
    IF generated = 0
        GENERATE_RANDOM_INT_IN_RANGE 0 25 rndnum            //NUMERO RANDOM QUE DEFINE SE O CARRO VAI OU N ESTRAGAR. DIMINUINDO O SEGUNDO ARGUMENTO AUMENTA A PROBABILIDADE E VICE-VERSA
        GENERATE_RANDOM_INT_IN_RANGE 0 300000 rndwait       //NUMERO RANDOM QUE DEFINE O TEMPO ATÉ O CARRO DO PLAYER ESTRAGAR. AUMENTANDO O SEGUNDO ARGUMENTO PODE FAZR O CARRO DEMORAR MAIS PRA ESTRAGAR E VICE-VERSA
        generated = 1
        IF rndnum < 4
            WAIT rndwait 
            BURST_CAR_TYRE plcar 1
            IF rndnum < 2
                SET_CAR_ENGINE_BROKEN plcar 1
                START_CAR_FIRE plcar carfire
                GOTO main_loop
            ENDIF
        ENDIF
        IF rndnum < 2                                       //AUMENTANDO ESTE AUMENTA A PROBABILIDADE DE O CARRO QUEBRAR
            WAIT rndwait                                    //TEMPO ATÉ O CARRO ESTRAGAR,NESSE CASO É USADO UM NUMERO ALEATÓRIO
            SET_CAR_ENGINE_BROKEN plcar 1
            START_CAR_FIRE plcar carfire
        ENDIF
    ENDIF
ENDIF
GOTO main_loop

}
SCRIPT_END