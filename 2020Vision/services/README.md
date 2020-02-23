# Services
These are the systemd unit files that allow for the vision processes to run in the background.
Systemd is the system and service manager for Debian.
It uses unit files to describe which processes to run as services and when they should run.

## Starting and Stopping Services
To start a service, run the following command:
```shell script
# Base format:
# sudo systemctl start <service name>

# Example:
sudo systemctl start turret.service
```

To stop a service, run the following command:
```shell script
# Base format:
# sudo systemctl stop <service name>

# Example:
sudo systemctl stop turret.service
```

## Determining Service Status
To get the status of a service, run the following:
```shell script
# Base format:
# sudo systemctl status <service name>

# Example:
sudo systemctl status turret.service
```

## Enabling and Disabling Start on Boot
To enable run on boot, run the following command:
```shell script
# Base format:
# sudo systemctl enable <service name>

# Example:
sudo systemctl enable turret.service
```

To disable run on boot, run the following command:
```shell script
# Base format:
# sudo systemctl disable <service name>

# Example:
sudo systemctl disable turret.service
```
