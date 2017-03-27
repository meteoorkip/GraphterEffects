grammar Graafvis;

import GraafvisVocab;

/* A Graafvis script consists out of a list of imports, label predicate generation and a list of clauses */
program: import_vis*
         node_label_gen?
         edge_label_gen?
         clause*
         EOF;

/** Import another vis file. The .vis is implied. */
import_vis: IMPORT_TOKEN STRING EOL;

/* Specify which labels should have generated identifiers: predicates and constants */
node_label_gen: NODE_LABEL_TOKEN COLON label (COMMA label)* EOL;
edge_label_gen: EDGE_LABEL_TOKEN COLON label (COMMA label)* EOL;

/* Define and rename a label */
label: STRING (RENAME_TOKEN ID)?;

/* Implicative clauses */
clause: (antecedent ARROW)? consequence EOL;

antecedent: propositional_formula;

propositional_formula: NOT propositional_formula
                     | propositional_formula bool_op propositional_formula
                     | PAR_OPEN propositional_formula PAR_CLOSE
                     | literal
                     ;

consequence: literal (COMMA literal)*;

/* Literals are atomic formulas or boolean expressions*/
literal: atom                   #atomLiteral
       | multi_atom             #multiAtomLiteral
       | num_expr               #numExprLiteral
       ;

/* Atoms are predicates applied to a tuple of terms */
atom: predicate PAR_OPEN (term (COMMA term)*)? PAR_CLOSE;

/* Language feature to apply one predicate to multiple tuples of terms */
multi_atom: predicate BRACE_OPEN term (COMMA term)* BRACE_CLOSE;

/* Predicates start with lowercase letter */
predicate: ID;

/* Terms are either ground terms, free variables, underscores or a tuple of more terms */
term: ground_term
    | variable
    | UNDERSCORE
    | PAR_OPEN term (COMMA term)* PAR_CLOSE
    ;

/* Ground terms contain no free variables */
ground_term: STRING
           | NUMBER
           | ID
           ;

/* Variables start with uppercase letter */
variable: HID;

num_expr: num_expr pow_op num_expr
        | num_expr mult_op num_expr
        | num_expr plus_op num_expr
        | PAR_OPEN num_expr PAR_CLOSE
        | variable
        | NUMBER
        ;

bool_op: COMMA | AND | OR;
eq_op: EQ | NQ | GT | LT | GE | LE;

pow_op: POW;
mult_op: MULT | DIV | MOD;
plus_op: PLUS | MINUS;

