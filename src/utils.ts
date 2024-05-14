
import { format, parseISO } from 'date-fns';
import ptBR from 'date-fns/locale/pt-BR';

export const formatDate = (date: string) => {

    const dateObject = new Date(date);
    console.log(dateObject);
    return format(parseISO(date), "dd 'de' MMM 'de' yyyy - kk'h'mm ", {
        locale: ptBR,
    });
}
