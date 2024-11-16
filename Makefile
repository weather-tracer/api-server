.PHONY:

# ==============================================================================
# Docker Control

HUB_NAME = someday94
HUB_USERNAME ?= $(shell bash -c 'read -p "Username: " username; echo $$username')
HUB_PASSWORD ?= $(shell bash -c 'read -s -p "Password: " pwd; echo $$pwd')

build_image:
	@read -p "Enter Version Name:" version; \
	docker rmi $(HUB_NAME)/weather-tracer-api-server:$$version; \
	docker build -t $(HUB_NAME)/weather-tracer-api-server:$$version -f ./Dockerfile .; \

push_image:
	@read -p "Enter Version Name:" version; \
	docker rmi $(HUB_NAME)/weather-tracer-api-server:$$version; \
	docker build -t $(HUB_NAME)/weather-tracer-api-server:$$version -f ./Dockerfile .; \
	docker push $(HUB_NAME)/weather-tracer-api-server:$$version; \