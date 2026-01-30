// ANTLR4 grammar for Python 3 (truncated for demo; use full grammar for production)
grammar Python3;

file_input: (stmt | NEWLINE)* EOF;
stmt: simple_stmt | compound_stmt;
simple_stmt: small_stmt NEWLINE;
small_stmt: expr_stmt;
expr_stmt: NAME '=' test | test;
test: NAME | NUMBER | 'None' | 'True' | 'False';
compound_stmt: funcdef;
funcdef: 'def' NAME parameters ':' suite;
parameters: '(' ')';
suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT;
NAME: [a-zA-Z_][a-zA-Z_0-9]*;
NUMBER: [0-9]+;
NEWLINE: '\r'? '\n';
INDENT: '    ';
DEDENT: ;
WS: [ \t]+ -> skip;
