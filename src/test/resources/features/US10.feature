Feature:Usuario cliente registra un nuevo envío

  Como usuario cliente quiero poder crear una notificación cuando cree un nuevo envío

  Scenario: El usuario cliente registra un nuevo envío de manera correcta
    Given el usuario se encuentre en la sección de envíos
    When realice un nuevo envío llenando de manera correcta los datos
    And presione el botón de Registrar
    Then se creará una notificación que indique que hay un nuevo envío
