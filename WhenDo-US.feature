Feature: WhenDo

  @WhenDo
  Scenario: Como un usuario simple
            Quiero crear un recordatorio
            Para almacenar y ser notificada.

    Given yo tengo abierta mi aplicacion WhenDo
    When yo selecciono el boton "add"
    And yo selecciono el boton "recordatorio"
    And yo selecciono datos para crear el Recordatorio
    And yo agrego el titulo "Title1" y la nota "This is my note"
    And yo selecciono el boton "save"
    Then el titulo "Title1" y la nota "This is my note" tienen que mostrarse en mi lista

