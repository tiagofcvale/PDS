package Aula08;

public class TFMain {
    public static void main(String[] args) {
        TodosFazem tf = new TodosFazem();

        String jan = "2026-01-10";
        String jun = "2026-06-15";
        String dec = "2026-12-20";

        // a) Contratar
        Employee maria = tf.hire("Maria Silva");
        maria.start(jan);

        maria.work();
        System.out.println();

        // b) Atribuir dinamicamente várias competências ao mesmo tempo
        // Maria passa a TeamMember e TeamLeader (ao mesmo tempo)
        maria = tf.assignTeamMember(maria);
        maria = tf.assignTeamLeader(maria);

        maria.work();

        // Para chamar métodos específicos (colaborate/plan/manage),
        // fazemos cast para o decorator concreto (porque o interface Employee é comum).
        ((TeamLeader) maria).plan();
        ((TeamMember) ((TeamLeader) maria).decorated).colaborate(); // maria é TeamLeader(TeamMember(BaseEmployee))
        System.out.println();

        // Ao longo do ano: Maria ganha mais uma responsabilidade (Manager)
        maria = tf.assignManager(maria);

        maria.work();
        ((Manager) maria).manage();
        System.out.println();

        // Exemplo de “remover” responsabilidades:
        // Como é Decorator, “remover” é voltar ao objecto interior (unwrap).
        // Aqui, removemos Manager (voltamos para TeamLeader(TeamMember(BaseEmployee))).
        maria = ((Manager) maria).decorated;

        System.out.println("Após remover Manager em " + jun);
        maria.work();
        System.out.println();

        // Terminar contrato
        maria.terminate(dec);
        maria.work(); // deve indicar que não está activa
    }
}