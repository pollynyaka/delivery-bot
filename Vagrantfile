# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|

  config.vm.define "tdb_33100" do |tdb_33100|
    tdb_33100.vm.box = "ubuntu/xenial64"
    tdb_33100.vm.box_check_update = false
    tdb_33100.vm.hostname = "tdb-33100"
    tdb_33100.vm.network "private_network", ip: "192.168.33.100"
    tdb_33100.vm.provider "virtualbox" do |vb|
      vb.cpus = 2
      vb.memory = "1024"
      vb.customize ["modifyvm", :id, "--cpuexecutioncap", "90"]
    end
  end

  config.vm.provision "shell" do |s|
    ssh_pub_key = File.readlines("#{Dir.home}/.ssh/id_rsa.pub").first.strip
    s.inline = <<-SHELL
      if [ ! -f ~/runonce ]
        then
          echo #{ssh_pub_key} >> /home/ubuntu/.ssh/authorized_keys
          touch ~/runonce
      fi
      apt-get update
      apt-get -y install htop vim

      #####
      # https://docs.docker.com/install/linux/docker-ce/ubuntu/
      #####

      apt-get -y install \
        apt-transport-https \
        ca-certificates \
        curl \
        software-properties-common

      curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -

      add-apt-repository \
        "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
        $(lsb_release -cs) \
        stable"

      apt-get update

      apt-get -y install docker-ce

      #####
      # https://docs.docker.com/install/linux/docker-ce/ubuntu/
      #####

      docker run --name tdb-postgres -d -p 5432:5432 \
        -e POSTGRES_PASSWORD=superPassword postgres:10.5

      docker build /vagrant/ -t delivery-bot:0.1

      docker run --name tdb-app -d -p 8080:8080 \
        -v /vagrant/logs:/usr/local/tomcat/logs delivery-bot:0.1

    SHELL
  end

end
