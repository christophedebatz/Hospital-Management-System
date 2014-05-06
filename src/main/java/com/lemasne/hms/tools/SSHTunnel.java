package com.lemasne.hms.tools;

import com.jcraft.jsch.*;

public class SSHTunnel {

    private String firstHost = "gandalf.ece.fr";
    private String secondHost = "sql-users.ece.fr";
    private int firstHostPort = 22;
    private int secondHostPort = 3305;
    private String username;
    private String password;
    private Session session;

    /* ************************
     *       Constructors     *
     ************************ */
    /**
     * Constructeur permettant la connexion Ã  un serveur via un double tunnel
     * SSH
     *
     * @param username Nom d'utilisateur ECE
     * @param password Mot de passe ECE
     * @param firstHost Premier hÃ´te avec lequel il faut Ã©tablir un tunnel SSH
     * @param secondHost Second hÃ´te avec lequel il faut Ã©tablir un tunnel SSH
     * @param firstHostPort Port utiliser pour se connecter au premier hÃ´te
     * @param secondHostPort Port utiliser pour se connecter au second hÃ´te
     */
    public SSHTunnel(String username, String password, String firstHost, String secondHost, int firstHostPort, int secondHostPort) {
        this.username = username;
        this.password = password;
        this.setFirstHost(firstHost);
        this.setSecondHost(secondHost);
        this.setFirstHostPort(firstHostPort);
        this.setSecondHostPort(secondHostPort);
    }

    /**
     * Constructeur permettant la connexion automatique au serveur de la base de
     * donnÃ©es ECE
     *
     * @param username Nom d'utilisateur ECE
     * @param password Mot de passe ECE
     */
    public SSHTunnel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /* ************************
     *         Methods        *
     ************************ */
    /**
     * Tente de se connecter au serveur
     *
     * @return TRUE si la connexion rÃ©ussie, FALSE sinon
     */
    @SuppressWarnings("CallToThreadDumpStack")
    public boolean connect() {
        if (this.session != null && this.session.isConnected()) {
            try {
                // Initialise la connexion
                JSch jsch = new JSch();
                this.session = jsch.getSession(this.getUsername(), this.getFirstHost(), this.getFirstHostPort());
                
                // Automatiser la connexion (ne pas afficher d'interface pour rentrer les mots de passe)
                this.session.setUserInfo(new SilentUserInfo(this.password));

                // Etablissement du premier tunnel SSH
                this.session.connect();

                // Etablissement du second tunnel SSH (port forwarding with option -L)
                int port = session.setPortForwardingL(this.getSecondHostPort(), this.getSecondHost(), this.getSecondHostPort());
                
                System.out.println("SSH connexion successful : localhost -> " + this.getFirstHost() + ":" + this.getFirstHostPort() + " -> " + " " + port + ":" + this.getSecondHost() + ":" + this.getSecondHostPort());
                
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return true;
        }
        this.session = null;
        return false;
    }

    /* ******************L******
     *    Getters & Setters   *
     ************************ */
    public String getFirstHost() {
        return firstHost;
    }

    private void setFirstHost(String firstHost) {
        this.firstHost = firstHost;
    }

    public String getSecondHost() {
        return secondHost;
    }

    private void setSecondHost(String secondHost) {
        this.secondHost = secondHost;
    }

    public int getFirstHostPort() {
        return firstHostPort;
    }

    private void setFirstHostPort(int firstHostPort) {
        this.firstHostPort = firstHostPort;
    }

    public int getSecondHostPort() {
        return secondHostPort;
    }

    private void setSecondHostPort(int secondHostPort) {
        this.secondHostPort = secondHostPort;
    }

    public String getUsername() {
        return username;
    }

    /* ************************
     *      Private class     *
     ************************ */
    /**
     * Classe gÃ©rant l'interaction de l'utilisateur lors de la connexion. Elle
     * automatise la connexion en fournissant les informations de connexion sans
     * les demander Ã  l'utilisateur
     */
    static class SilentUserInfo implements UserInfo, UIKeyboardInteractive {

        private String password;

        public SilentUserInfo(String password) {
            this.password = password;
        }

        @Override
        public String getPassword() {
            return this.password;
        }

        @Override
        public boolean promptYesNo(String str) {
            return true;
        }

        @Override
        public String getPassphrase() {
            return null;
        }

        @Override
        public boolean promptPassphrase(String message) {
            return true;
        }

        @Override
        public boolean promptPassword(String message) {
            return true;
        }

        @Override
        public void showMessage(String message) {
        }

        @Override
        public String[] promptKeyboardInteractive(String destination,
                String name,
                String instruction,
                String[] prompt,
                boolean[] echo) {
            return null;
        }
    }
}
