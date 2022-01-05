# Controle-de-Alunos
Controle de alunos - Lab04 da disciplina de Linguagem de Programação 2.

Neste laboratório, construiremos um sistema que será a base do controle de alunos de Programação II. Como sistema, deve ser possível primeiramente cadastrar e consultar alunos. Cada aluno tem uma matrícula, nome e curso (todos Strings). Cada consulta deve procurar o aluno a partir de sua matrícula. O aluno é identificado unicamente pela matrícula e não deve ser possível alterar os dados dos alunos uma vez cadastrado.

Os alunos podem se juntar em grupos de estudo. Cada grupo de estudo tem um tema (descrito por uma String, como por exemplo o tema “Coleções”) e é formado por um conjunto de alunos. O aluno pode estar em mais de um grupo de estudo. Deve ser possível colocar alunos em tais grupos a partir de sua matrícula. Um grupo pode ter restrição quanto ao curso dos alunos e esta restrição será definida no momento da criação do grupo; nesse caso, apenas alunos de um determinado curso poderá ser alocado ao grupo. Cada grupo é identificado unicamente a partir do nome do grupo.

Por fim, durante as aulas, os alunos costumam fazer exercícios em quadro e responder perguntas feitas pelo professor. O professor deseja manter um registro dos alunos que responderam tais perguntas. É possível que o mesmo aluno seja questionado mais de uma vez e preciso registrar a ordem que os mesmos responderam.

